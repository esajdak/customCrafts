package safeToDeleteIThink;

import edu.matc.persistence.GenericDao;
import edu.matc.entity.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/userSearch")
public class UserSearch {
    GenericDao dao = new GenericDao(edu.matc.entity.User.class);

    @GET
    @Produces("text/html")
    public Response getAllUsers() {
        List<User> users = dao.getAll();
        String output = "<html><table><tr><th>First Name</th><th>Last Name</th><th>Email</th></tr>";
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            output += "<tr><td>" + user.getFirstName() + "</td><td>" + user.getLastName() + "</td><td>" + user.getEmail() + "</td></tr>";
        }
        return Response.status(200).entity(output).build();
    }

}
