package ilife.song.service;

import ilife.song.model.Album;
import ilife.song.model.Artist;
import ilife.song.model.Song;

import java.util.List;

public interface SongService {

	public Song addSong(Song song);
	
	public Album addAlbum(Album album);

	public void removeSong(Long songId);

	public void updateSong(Song song);

	public Song getSongById(Long songId);

	public List<Song> getSongsByName(String name);

	public List<Song> getSongsByAlbum(Album album);

	public List<Song> getSongsByArtist(Artist artist);

	public List<Song> getSongsByLyric(String searchParam);

	public String getLyricBySong(Long songId);

	public void addLyricForSong(Long songId, String lyric);

	
	
}
