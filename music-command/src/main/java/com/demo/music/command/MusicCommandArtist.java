package com.demo.music.command;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.aries.blueprint.annotation.service.ServiceProperty;
import org.apache.karaf.shell.table.ShellTable;

import com.demo.music.model.Artist;
import com.demo.music.model.ServiceDAOGenneric;
import com.demo.music.model.Song;

@Service(classes = MusicCommandArtist.class, properties = {
		@ServiceProperty(name = "osgi.command.scope", values = "artist-music"),
		@ServiceProperty(name = "osgi.command.function", values = { "add", "list", "update" }) })
@Singleton
public class MusicCommandArtist {
	@Inject
	@Reference(filter = "(artist.service.dao=true)")
	private ServiceDAOGenneric<Artist> m_artistService;
	@Inject
	@Reference(filter = "(song.service.dao=true)")
	private ServiceDAOGenneric<Song> m_songService;

	public void add(String name, int songId) {
		Artist artist = new Artist();
		artist.setArtistName(name);
		Song song = m_songService.findById(songId);
		if (song != null)
			artist.setSongById(song);
		m_artistService.add(artist);
	}

	public void list() {
		System.out.println("new");
		ShellTable table = new ShellTable();
		table.column("id");
		table.column("name");
		table.column("song_name");

		Set<Artist> list = m_artistService.getAll();
		for (Artist artist : list)
			table.addRow().addContent(artist.getArtistId(), artist.getArtistName(), artist.getSongById().getSongName());
		table.print(System.out);
	}

	public void update(int artistId, String artistName) {
		Artist artist = m_artistService.findById(artistId);
		if (artist == null)
			return;
		artist.setArtistName(artistName);
		m_artistService.update(artist);
	}
}
