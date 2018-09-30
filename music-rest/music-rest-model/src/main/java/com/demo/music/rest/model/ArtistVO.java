package com.demo.music.rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.demo.music.model.Artist;

@XmlRootElement
public class ArtistVO implements Serializable {
	private int m_artistId;
	private String m_artistName;

	public ArtistVO() {
		super();
	}

	public ArtistVO(int artistId, String artistName) {
		super();
		this.m_artistId = artistId;
		this.m_artistName = artistName;
	}

	public ArtistVO(Artist artist) {
		this.m_artistId = artist.getArtistId();
		this.m_artistName = artist.getArtistName();
	}

	public int getArtistId() {
		return m_artistId;
	}

	public void setArtistId(int artistId) {
		this.m_artistId = artistId;
	}

	public String getArtistName() {
		return m_artistName;
	}

	public void setArtistName(String artistName) {
		this.m_artistName = artistName;
	}

}
