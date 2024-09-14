package spotifyclone;

import java.io.Serializable;
import java.util.ArrayList;

public class Song  implements Serializable{
    protected String name;
    protected String songpath;
    protected String songcoverpagepath;
    protected int likes;
    protected artist creator;
    protected String uploadyear;
    protected String duration;
    protected static ArrayList<Song> allsongs= new ArrayList<Song>();
    public Song(){

    }
    public Song(String n,String sp, String cpp,int l,artist c,String u,String d){
        name=n;
        songpath=sp;
        songcoverpagepath=cpp;
        likes=l;
        creator=c;
        uploadyear=u;
        duration=d;

    }
    public static Song getsongbyname(String n){
        for(int i=0;i<allsongs.size();i++){
            if(allsongs.get(i).getName().equals(n)){
                return allsongs.get(i);
            }
        }
        return null;
    }
    public static void addtoallsongs(Song newsong){
        allsongs.add(newsong);
    }
    public static ArrayList<Song> getallsongs(){
        return allsongs;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSongpath() {
        return songpath;
    }
    public void setSongpath(String songpath) {
        this.songpath = songpath;
    }
    public String getSongcoverpagepath() {
        return songcoverpagepath;
    }
    public void setSongcoverpagepath(String songcoverpagepath) {
        this.songcoverpagepath = songcoverpagepath;
    }
    public int getLikes() {
        return likes;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }
    public artist getCreator() {
        return creator;
    }
    public void setCreator(artist creator) {
        this.creator = creator;
    }
    public String getUploadyear() {
        return uploadyear;
    }
    public void setUploadyear(String uploadyear) {
        this.uploadyear = uploadyear;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    

}
