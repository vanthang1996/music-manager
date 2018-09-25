package com.demo.music.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "song")
@XmlRootElement
public class Song implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
	@Column(name = "id_song", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int m_songId;

	@Column(name = "name")
	private String m_songName;
// have to mappyedBy otherwise jpa auto gennerate manytomany relationship wit artist table
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL }, mappedBy = "m_songById")
	private Set<Artist> m_artists = new HashSet<Artist>();

	public Song() {
		super();
	}

	public Song(int songId, String songName, Set<Artist> artists) {
		super();
		this.m_songId = songId;
		this.m_songName = songName;
		this.m_artists = artists;
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

	public void setSongName(String songName) {
		this.m_songName = songName;
	}

	public Set<Artist> getArtists() {
		return m_artists;
	}

	public void setArtists(Set<Artist> artists) {
		this.m_artists = artists;
	}

	@Override
	public String toString() {
		return "Song [songId=" + m_songId + ", songName=" + m_songName + ", artists=" + m_artists + "]";
	}

}
