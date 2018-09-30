package com.demo.music.persistence;

import org.apache.aries.blueprint.annotation.bean.Bean;
import org.apache.aries.blueprint.annotation.service.Service;
import org.apache.aries.blueprint.annotation.service.ServiceProperty;

import com.demo.music.model.ServiceDAOGenneric;
import com.demo.music.model.Song;

@Service(classes = ServiceDAOGenneric.class, properties = {
		@ServiceProperty(name = "song.service.dao", values = "true") })
@Bean(id = "song-dao")
public class SongDao extends GennericDAOImpl<Song> {
	/*
	 * @Inject
	 * 
	 * @Named(value = "genneric-dao") private ServiceDAOGenneric<Artist>
	 * daoGenneric;
	 */
	/*
	 * @Bean public MysqlDataSource dataSource() { MysqlDataSource dataSource = new
	 * MysqlDataSource(); dataSource.setServerName("localhost");
	 * dataSource.setDatabaseName("music-manage"); dataSource.setPort(3306);
	 * dataSource.setUser("root"); dataSource.setPassword(""); return dataSource; }
	 */

}
