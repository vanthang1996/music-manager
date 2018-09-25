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

@Service(classes = MusicCommandArtist.class, properties = {
		@ServiceProperty(name = "osgi.command.scope", values = "artist-music"),
		@ServiceProperty(name = "osgi.command.function", values = { "add", "list", "update" }) })
@Singleton
public class MusicCommandArtist {
	@Inject
	/* @Reference(filter = "(music.genneric.dao=true)") */
	@Reference(componentName = "genneric-dao")
	ServiceDAOGenneric<Artist> m_serviceDAOGennericArtist;

	public void setGenneric() {
		this.m_serviceDAOGennericArtist.setClazz(Artist.class);
	}

	public void add(String name) {
		Artist artist = new Artist();
		artist.setArtistName(name);
		setGenneric();
		m_serviceDAOGennericArtist.add(artist);
	}

	public void list() {
		ShellTable table = new ShellTable();
		table.column("id");
		table.column("name");
		setGenneric();
		Set<Artist> list = m_serviceDAOGennericArtist.getAll();
		for (Artist artist : list) {
			table.addRow().addContent(artist.getArtistId(), artist.getArtistName());
		}
		table.print(System.out);
		System.out.println(m_serviceDAOGennericArtist);
	}

	public void update(int artistId, String artistName) {
		Artist artist = new Artist();
		artist.setArtistId(artistId);
		artist.setArtistName(artistName);
		m_serviceDAOGennericArtist.update(artistId, artist);
	}
}
