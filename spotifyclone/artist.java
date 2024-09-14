package spotifyclone;

import java.util.ArrayList;

public class artist extends profile {
    ArrayList<Song> uploads= new ArrayList<Song>();
    ArrayList<Song> topSong= new ArrayList<Song>();
    ArrayList<album> artistalbums= new ArrayList<album>();
    artist(){ super();}
    public artist(String n){
        super.setName(n);
    }
    public artist(ArrayList<Song> uploads, ArrayList<Song> topSong, ArrayList<album> artistalbums) {
        super();
        this.uploads = uploads;
        this.topSong = topSong;
        this.artistalbums = artistalbums;
    }
    public artist(String e,String p,String n, String u, String path, ArrayList<Song> uploads, ArrayList<Song> topSong,
            ArrayList<album> artistalbums) {
        super(n, u, path,e,p);
        this.uploads = uploads;
        this.topSong = topSong;
        this.artistalbums = artistalbums;
    }
    public ArrayList<Song> getUploads() {
        return uploads;
    }
    public void setUploads(ArrayList<Song> uploads) {
        this.uploads = uploads;
    }
    public ArrayList<Song> getTopSong() {
        return topSong;
    }
    public void setTopSong(ArrayList<Song> topSong) {
        this.topSong = topSong;
    }
    public ArrayList<album> getArtistalbums() {
        return artistalbums;
    }
    public void setArtistalbums(ArrayList<album> artistalbums) {
        this.artistalbums = artistalbums;
    }
    
    
}
