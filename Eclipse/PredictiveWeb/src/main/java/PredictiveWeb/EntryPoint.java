package PredictiveWeb;

import java.util.ArrayList;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

@Path("/entry-point")
public class EntryPoint {
	static ArrayList<ComPortStream> list = new ArrayList<ComPortStream>();
	ArrayList<PlayerProfile> players = new ArrayList<PlayerProfile>();

	@GET
	@Path("/getData")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPredictions() {
		System.out.println("getData");
		Gson gson = new Gson();

		KxTutorial kt = new KxTutorial();
		String[] iArgs = new String[2];
		iArgs[0]="level1";
		kt.main(iArgs);
		
		// just here as an example for how you can return java objects as json
		
		ComPortStream obj = new ComPortStream(null, null, "1", "2", false);
		String jsonInString = gson.toJson(obj);
		return Response.ok(jsonInString).build();
	}

	@GET
	@Path("/getLeaderBoard")
	@Produces(MediaType.TEXT_PLAIN)
	public Response getLeaderBoard() {
		System.out.println("getLeaderBoard");
		String table = DbUtil.viewOverallStats();
		return Response.ok(table).build();
	}

	@POST
	@Path("/createPlayer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPlayer(String data) {
		System.out.println(data);
		Map<String, String> map = Util.parseString(data);
		// PlayerProfile playerProfile = new PlayerProfile(map.get("name"),
		// map.get("age"), true);
		String jsonInString = DbUtil.insertDetailsHana(map.get("firstName"), map.get("name"),
				Integer.parseInt(map.get("drive")), Integer.parseInt(map.get("coffee")),
				Integer.parseInt(map.get("gender")), Integer.parseInt(map.get("age")));

		return Response.ok(jsonInString).build();
	}

	@POST
	@Path("/startLevel1")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response startLevel1(String data) throws Exception {
		System.out.println(data);

		// just here for testing:
		SerialConnect sc = new SerialConnect();
		ArrayList<ComPortStream> list = sc.main(players);

		return Response.ok("started").build();
	}

	@POST
	@Path("/startLevel4")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response startLevel4(String data) throws Exception {
		System.out.println(data);
		// call start level 4
		return Response.ok("started").build();
	}

	@POST
	@Path("/getScores")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getScores() throws Exception {
		return Response.ok("scores").build();
	}
}