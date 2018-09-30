package com.demo.music.rest.impl;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Singleton;
import javax.ws.rs.Produces;

import org.apache.aries.blueprint.annotation.service.Service;

import com.demo.music.model.Artist;
import com.demo.music.model.ServiceDAOGenneric;
import com.demo.music.model.Song;
import com.demo.music.rest.model.ArtistVO;
import com.demo.music.rest.model.SongRestService;
import com.demo.music.rest.model.SongVO;

@Produces
public class SongRestServiceImpl implements SongRestService {

	ServiceDAOGenneric<Song> m_songService;

	ServiceDAOGenneric<Artist> m_artistService;

	@Override
	public Set<SongVO> getSongAll() {
		/* return m_songService.getAll(); */
		Set<SongVO> setSongVO = new HashSet<>();
		Set<Song> setSong = m_songService.getAll();
		for (Song song : setSong)
			setSongVO.add(new SongVO(song));
		// not return if not have @xmlRootElemnet
		return setSongVO;
	}

	@Override
	public Set<ArtistVO> getArtistAll() {
		Set<Artist> setArtist = m_artistService.getAll();
		Set<ArtistVO> setArtistVO = new HashSet<>();
		for (Artist artist : setArtist)
			setArtistVO.add(new ArtistVO(artist));
		return setArtistVO;
	}

}
