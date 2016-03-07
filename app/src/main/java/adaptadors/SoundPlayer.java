package adaptadors;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;

import com.high_technology_software.android.pauapp.R;

/**
 * Created by christianalos on 7/3/16.
 */
public class SoundPlayer {
    private static SoundPlayer INSTANCIA = null;
    private MediaPlayer pp;
    private int po = 0;
    private Context mContext;
    private int nu = 0;
    private boolean isActive;

    private SoundPlayer(Context context){
        mContext = context;
        isActive = false;
    }
    public static SoundPlayer getInstance(Context context) {
        if (INSTANCIA == null){
            INSTANCIA = new SoundPlayer(context);
        }
        return INSTANCIA;
    }


    public void limpiarMP(){
        if(pp!=null){
            pp.release();

        }
    }
    public void sonido (View v, Uri path){
        //pasar ruta
        limpiarMP();
        pp = MediaPlayer.create(mContext, path);
        nu=0;
        pp.start();
        isActive = true;
    }
    public void sonido (Uri path){
        //pasar ruta
        limpiarMP();
        pp = MediaPlayer.create(mContext, path);
        nu=0;
        pp.start();
        isActive = true;
    }
    public void pause (View v){
        if(pp!=null && pp.isPlaying()){
            po=pp.getCurrentPosition();
            //btn2.setText ("Reaunudar");
            pp.pause();
            nu= 1;
            isActive = false;
        }
    }
    public void pause (){
        if(pp!=null && pp.isPlaying()){
            po=pp.getCurrentPosition();
            //btn2.setText ("Reaunudar");
            pp.pause();
            nu= 1;
            isActive = false;
        }
    }
    public void detener (View v){
        if(pp!=null){
            pp.stop();
            po=0;
            nu=0;
            limpiarMP();
            isActive = false;
        }
    }
    public void detener (){
        if(pp!=null){
            pp.stop();
            po=0;
            nu=0;
            limpiarMP();
            isActive = false;
        }
    }

    public boolean canExit() {
        if (!pp.isPlaying()) {
            isActive = false;
        }
        return !isActive;
    }
}
