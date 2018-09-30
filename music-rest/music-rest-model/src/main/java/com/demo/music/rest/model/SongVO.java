package com.demo.music.rest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;

import com.demo.music.model.Artist;
import com.demo.music.model.Song;

@XmlRootElement
public class SongVO implements Serializable {
	private int m_songId;
	private String m_songName;
	private Set<ArtistVO> m_artists = new HashSet<>();

	public SongVO(int songId, String songName, Set<ArtistVO> artists) {
		super();
		this.m_songId = songId;
		this.m_songName = songName;
		this.m_artists = artists;
	}

	public SongVO(Song song) {
		this.m_songId = song.getSongId();
		this.m_songName = song.getSongName();
		Set<Artist> artists = song.getArtists();
		for (Artist artist : artists) {
			this.m_artists.add(new ArtistVO(artist));
		}
	}

	public int getSongId() {
		return m_songId;
	}

	public void setSongId(int songId) {
		this.m_songId = songId;
	}

	public String getSongName() {
		return m_songName;
	}

	public SongVO() {
		super();
	}

	public void setSongName(String songName) {
		this.m_songName = songName;
	}

	public Set<ArtistVO> getArtists() {
		return m_artists;
	}

	public void setArtists(Set<ArtistVO> artists) {
		this.m_artists = artists;
	}

}
