package com.demo.music.rest.model;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public interface SongRestService {
	@GET
	@Path("/songs")
	public Set<SongVO> getSongAll();

	@GET
	@Path("/artists")
	public Set<ArtistVO> getArtistAll();
}
