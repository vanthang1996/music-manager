package com.demo.music.command;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.PostLoad;

import org.apache.aries.blueprint.annotation.service.Reference;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.aries.blueprint.annotation.service.ServiceProperty;
import org.apache.karaf.shell.table.ShellTable;

import com.demo.music.model.ServiceDAOGenneric;
import com.demo.music.model.Song;;

@Service(classes = MusicCommandSong.class, properties = {
		@ServiceProperty(name = "osgi.command.scope", values = "song-music"),
		@ServiceProperty(name = "osgi.command.function", values = { "add", "list" }) })
@Singleton
public class MusicCommandSong {

	@Inject
	/* @Reference(filter = "(music.genneric.dao=true)") */
	@Reference(componentName = "genneric-dao")
	ServiceDAOGenneric<Song> m_serviceDAOGennericSong;

	@PostLoad
	public void setGenneric() {
		m_serviceDAOGennericSong.setClazz(Song.class);
	}

	public void add(String name, int... artisIds) {
	}

	public void list() {
		ShellTable table = new ShellTable();
		table.column("id");
		table.column("name");
		table.column("artistId");
		setGenneric();
		Set<Song> list = m_serviceDAOGennericSong.getAll();
		for (Song song : list)
			table.addRow().addContent(song.getSongId(), song.getSongName(), song.getArtists());
		table.print(System.out);
		System.out.println(m_serviceDAOGennericSong);

	}

}
