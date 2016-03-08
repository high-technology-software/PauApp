package adaptadors;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;


/**
 * Created by christianalos on 7/3/16.
 */
public class SoundPlayer {
    private MediaPlayer pp;
    private int po;
    private Context mContext;

    private Uri path;
    public SoundPlayer(Context context, Uri path){
        mContext = context;
        pp = MediaPlayer.create(mContext, path);
        po = 0;

    }



    public void play (){
        //pasar ruta
        pp.start();

    }

    public void pause (){
        if(pp.isPlaying()){
            po=pp.getCurrentPosition();
            pp.pause();
        }
    }


    public void detener (){
        if(pp!=null){
            pp.stop();
            po=0;
        }
    }

    public boolean isPlaying() {
        return pp.isPlaying();
    }
}
