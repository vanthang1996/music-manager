package com.demo.music.persistence;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.aries.blueprint.annotation.service.ServiceProperty;

import com.demo.music.model.Artist;
import com.demo.music.model.ServiceDAOGenneric;

@Service(classes = ServiceDAOGenneric.class, properties = {
		@ServiceProperty(name = "artist.service.dao", values = "true") })
@Bean(id = "artist-dao")

public class ArtistDao extends GennericDAOImpl<Artist> {

}
