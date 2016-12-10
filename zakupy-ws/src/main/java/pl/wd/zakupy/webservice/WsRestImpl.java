package pl.wd.zakupy.webservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import pl.wd.zakupy.web.DataContainer;

@Consumes("application/xml")
@Path("zakupy")
public class WsRestImpl {
	static Logger logger = Logger.getLogger("pl.wd.zakupy.webservice.WsRestImpl");

	@GET
	@Path("/get-list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getList(@QueryParam("clientId") String clientId) {
		String list = DataContainer.getInstance().getClient2List().get(clientId);

		String output = "[]";
		if (list == null) {

		} else {
			output = list;
			//output = "[ {\"ware\": \"maslo\", \"bought\": true}, {\"ware\": \"ser\", \"bought\": true}, {\"ware\": \"jablka\", \"bought\": false}, {\"ware\": \"piwo\", \"bought\": false}, {\"ware\": \"chleb\", \"bought\": false} ]";

		}
		
		
		// @Consumes(MediaType.APPLICATION_JSON)

		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/saveList")
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveList(@QueryParam("clientId") String clientId, @QueryParam("list") String list) {
		DataContainer.getInstance().getClient2List().put(clientId, list);
		String output = "";

		DataContainer.getInstance().getClient2List().put(clientId, list);

		return Response.status(200).entity(output).build();
	}

}
