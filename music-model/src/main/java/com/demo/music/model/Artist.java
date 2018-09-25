package com.demo.music.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "artist")
@XmlRootElement
public class Artist implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_artist")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int m_artistId;

	@Column(name = "name")
	private String m_artistName;

	@ManyToOne(optional = false)
	// has joinColume to collum name of table not gennerate auto, default
	// filedName+idClass
	@JoinColumn(name = "id_song", nullable = false)
	private Song m_songById = new Song();

	public Artist() {
		super();
	}

	public Artist(int artistId, String artistName) {
		super();
		this.m_artistId = artistId;
		this.m_artistName = artistName;
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

	public Song getSongById() {
		return m_songById;
	}

	public void setSongById(Song songById) {
		this.m_songById = songById;
	}

	@Override
	public String toString() {
		return "Artist [artistId=" + m_artistId + ", artistName=" + m_artistName + "]";
	}

}
