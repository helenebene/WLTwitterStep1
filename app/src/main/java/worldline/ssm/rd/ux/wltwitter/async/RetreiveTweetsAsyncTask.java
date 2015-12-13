package worldline.ssm.rd.ux.wltwitter.async;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import worldline.ssm.rd.ux.wltwitter.helpers.TwitterHelper;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.ui.fragments.TweetsFragment;

/**
 * Created by isen on 13/12/2015.
 */
public class RetreiveTweetsAsyncTask extends AsyncTask<String, Integer,List<Tweet>> {

    private TweetChangeListener mListener;

    @Override
    protected List<Tweet> doInBackground (String... params) {

        // on vérifie si le login et le password ne sont pas nuls
        if ((null != params) && (params.length > 0))
        {
            // si c'est le cas, on appel TwitterHelper.getTweetsOfUser(params[0]), params[0] correspondant au login
            // la classe TwitterHelper se trouve dans le helpers
            return TwitterHelper.getTweetsOfUser(params[0]);
        }
        return  TwitterHelper.getFakeTweets();
    }

    @Override
    protected void onPostExecute(List<Tweet> result) {
        super.onPostExecute(result);

        if (null != mListener) {
            // on utilise le Log.d pour débuger,
            // on observe ainsi dans le monitor, l'apparition de "D/TweetAsyncTask"
            //Log.d("TweetAsyncTask",tweet.text);
            mListener.onTweetRetrieved(result);

        }
    }
}
