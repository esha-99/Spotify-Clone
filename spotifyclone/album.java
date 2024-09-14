package spotifyclone;

import java.io.Serializable;
import java.util.ArrayList;

public class album implements Serializable{
    protected int noofsongs;
    protected ArrayList<Song> albumsongs= new ArrayList<Song>();
    protected String albumname;
    protected String playtime;
    protected String creator;

    public album(){

    }
    public album(String n){
        albumname=n;
        noofsongs=0;
        playtime="00:00";
        creator=null;
    }
    public album(int songs,ArrayList<Song> songsinalbum, String n){
        noofsongs=songs;
        albumsongs=songsinalbum;
        albumname= n;
        playtime=calculateTotalTime(albumsongs);
    }

    public int getNoofsongs() {
        return albumsongs.size();
    }

    public void setNoofsongs(int noofsongs) {
        this.noofsongs = noofsongs;
    }

    public ArrayList<Song> getAlbumsongs() {
        return albumsongs;
    }

    public void setAlbumsongs(ArrayList<Song> albumsongs) {
        this.albumsongs = albumsongs;
    }

    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    public String getPlaytime() {
        return this.calculateTotalTime(albumsongs);
    }

    public void setPlaytime() {
        this.playtime = calculateTotalTime(albumsongs);
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    

    public String calculateTotalTime(ArrayList<Song> albumsongs2) {
        int totalSeconds = 0;

        for (int i=0;i<albumsongs2.size();i++) {
            String[] timeParts = albumsongs2.get(i).getDuration().split(":");
            int minutes = Integer.parseInt(timeParts[0]);
            int seconds = Integer.parseInt(timeParts[1]);
            totalSeconds += minutes * 60 + seconds;
        }

        int totalMinutes = totalSeconds / 60;
        int remainingSeconds = totalSeconds % 60;
        return totalMinutes + ":" + remainingSeconds;
    }
    public void addtoalbum(Song tempSong){
        albumsongs.add(tempSong);
    }
    public void removefromalbum(Song tempSong){
        albumsongs.remove(tempSong);
    }

}
