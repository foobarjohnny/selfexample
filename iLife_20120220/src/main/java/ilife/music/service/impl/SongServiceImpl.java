package ilife.music.service.impl;

import ilife.music.dao.SongDao;
import ilife.music.model.Song;
import ilife.music.service.SongService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author liujijun
 * 
 */
@Service("songService")
public class SongServiceImpl implements SongService {
	@Autowired
	private SongDao songDao;

	public List<Song> search(String keywords) {
		return songDao.search(keywords);
	}

}
