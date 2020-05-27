package com.uk.instagramui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.uk.instagramui.Activities.MainActivity;
import com.uk.instagramui.Adapters.TimelinePostAdapter;
import com.uk.instagramui.AppController;
import com.uk.instagramui.Model.TimelinePost;
import com.uk.instagramui.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment {
	
	private RecyclerView rvPosts;
	private TimelinePostAdapter postAdapter;
	private ArrayList<TimelinePost> posts;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.home_layout, container, false);
	}

	public static HomeFragment getInstance(){
		return new HomeFragment();
	}
	
	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		
		posts = new ArrayList<>();
		rvPosts = view.findViewById(R.id.rvPosts);
		
		//Populating posts
		//posts.add(new TimelinePost(MainActivity.images[0], "schwarzenegger", MainActivity.images[1],112, "How is that UI boy?", "10 min ago"));
		//posts.add(new TimelinePost(MainActivity.images[3], "Conor McGregor", MainActivity.images[3],200, "World Champion!", "2 hr ago"));
		
		rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
		
		postAdapter = new TimelinePostAdapter(getContext(), posts);
		rvPosts.setAdapter(postAdapter);
		getData(0);
	}

	public void getData(final int id) {

		StringRequest request = new StringRequest(Request.Method.POST,"https://lithics.in/apis/mauka/getOpportunities.php", new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				Log.d("Response",response);
				try {
					JSONArray  jsonArray = new JSONArray(response);
					for(int i=0;i<jsonArray.length();i++) {
						JSONObject obj = jsonArray.getJSONObject(i);
						posts.add(new TimelinePost(obj.getString("IMAGE"),obj.getString("HEADLINE"),obj.getString("IMAGE"),100,obj.getString("DESCRIPTION"),obj.getString("DEADLINE")));
					}
					postAdapter = new TimelinePostAdapter(getContext(), posts);
					rvPosts.setAdapter(postAdapter);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {

			}
		}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
				JSONArray jO = new JSONArray();
				jO.put("admission");
				jO.put("fellowship");
				params.put("id", "595");
				params.put("page", "0");
				params.put("language_id", "29");
				params.put("opp_id", String.valueOf(id));
				params.put("tags", jO.toString());
				return params;
			}
		};
		AppController.getInstance().addToRequestQueue(request, "dashboard_data");
	}



}
