package worldline.ssm.rd.ux.wltwitter.ui.fragments;

import worldline.ssm.rd.ux.wltwitter.R;
import worldline.ssm.rd.ux.wltwitter.async.RetreiveTweetsAsyncTask;
import worldline.ssm.rd.ux.wltwitter.interfaces.TweetChangeListener;
import worldline.ssm.rd.ux.wltwitter.pojo.Tweet;
import worldline.ssm.rd.ux.wltwitter.utils.PreferenceUtils;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TweetsFragment extends Fragment implements TweetChangeListener {

	private RetreiveTweetsAsyncTask mRetreiveTweetsAsyncTask;

	private ListView mListView;

	public TweetsFragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// on crée une relation entre la classe TweetsFragment et layout ragment_wltwitter
		View rootView = inflater.inflate(R.layout.fragment_wltwitter, container, false);

		//Get the ListView
		mListView = (ListView) rootView.findViewById(R.id.tweetsListView);

		return rootView;
	}

	@Override
	public void onStart() {
		super.onStart();

		// On start lauch our AsncTask to retrieve the Tweets of the user
		final String login = PreferenceUtils.getLogin();

		if (!TextUtils.isEmpty(login))
		{
			mRetreiveTweetsAsyncTask = new RetreiveTweetsAsyncTask();
			mRetreiveTweetsAsyncTask.execute(login);
		}

	}

	@Override
	public void onTweetRetrieved(List<Tweet> tweets) {

		//Set the adapter
		final ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(getActivity(), android.R.layout.simple_list_item_1, tweets);
		mListView.setAdapter(adapter);
	}
}
