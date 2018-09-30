package com.demo.music.command;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.aries.blueprint.annotation.service.ServiceProperty;
import org.apache.karaf.shell.table.ShellTable;

import com.demo.music.model.Artist;
import com.demo.music.model.ServiceDAOGenneric;
import com.demo.music.model.Song;;

@Service(classes = MusicCommandSong.class, properties = {
		@ServiceProperty(name = "osgi.command.scope", values = "song-music"),
		@ServiceProperty(name = "osgi.command.function", values = { "add", "list", "find" }) })
@Singleton
public class MusicCommandSong {
	@Inject
	@Reference(filter = "(song.service.dao=true)")
	ServiceDAOGenneric<Song> m_songService;
	@Inject
	@Reference(filter = "(artist.service.dao=true)")
	ServiceDAOGenneric<Artist> m_artistService;

	public void add(String name/* , int... artistIds */) {
		System.out.println("call song add!");
		Song song = new Song();
		song.setSongName(name);
		/*
		 * Set<Artist> artists = new HashSet<Artist>(); Artist artist = null; for (int
		 * artistId : artistIds) { artist = m_artistService.findById(artistId); if
		 * (artist != null) artists.add(artist); } song.setArtists(artists);
		 */
		m_songService.add(song);
	}

	public void list() {
		ShellTable table = new ShellTable();
		table.column("id");
		table.column("name");
		table.column("artistId");
		Set<Song> list = m_songService.getAll();
		List<String> names = new LinkedList<String>();
		Set<Artist> artists = null;
		for (Song song : list) {
			artists = song.getArtists();
			for (Artist artist : artists)
				names.add(artist.getArtistName());
			table.addRow().addContent(song.getSongId(), song.getSongName(), Arrays.toString(names.toArray()));
			names.clear();
		}
		table.print(System.out);
	}

	public void remove(int id) {
		m_songService.delete(id);
	}

	public void find(int id) {
		System.out.println(m_songService.findById(id));
	}

}
