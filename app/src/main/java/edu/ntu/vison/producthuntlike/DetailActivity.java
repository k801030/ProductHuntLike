package edu.ntu.vison.producthuntlike;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.ntu.vison.producthuntlike.model.Comment;
import edu.ntu.vison.producthuntlike.model.ProductItem;
import edu.ntu.vison.producthuntlike.model.ProductItemDetail;


public class DetailActivity extends Activity {
    public final static String PRODUCT_ID = "edu.ntu.vison.producthuntlike.product_id";
    private int product_id;
    private View view;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        Intent intent = getIntent();
        product_id = intent.getIntExtra(PRODUCT_ID, 0);

        setContentView(R.layout.activity_detail);
        view = this.findViewById(android.R.id.content);
        view.setVisibility(View.INVISIBLE);


        // volley request
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = RequestHandler.getPostDetail(new RequestHandler.getPostDetailCallback() {
            @Override
            public void onSuccess(ProductItemDetail result) {
                // declare components
                Button topview_votes = (Button) findViewById(R.id.detail_topview_votes);
                TextView topview_name = (TextView) findViewById(R.id.detail_topview_name);
                TextView topview_tagline = (TextView) findViewById(R.id.detail_topview_tagline);
                TextView topview_footer = (TextView) findViewById(R.id.detail_topview_footer);
                ExpandableListView comments_listview = (ExpandableListView) findViewById(R.id.detail_comment_listview);

                topview_votes.setText("▲\n" + result.getVotesCount());
                topview_name.setText(result.getName());
                topview_tagline.setText(result.getTagline());
                topview_footer.setText("via " + result.getUser().getName() + " 1 hour ago.");


                // Prepare for data
                HashMap<Integer, ArrayList<Comment>> commentChildList = new HashMap<Integer, ArrayList<Comment>>();
                for (int i=0;i<result.getComments().size();i++) {
                    commentChildList.put(i, result.getComments().get(i).getChildComments());
                }
                CommentListAdapter  listAdapter = new CommentListAdapter(context, result.getComments(), commentChildList);
                comments_listview.setAdapter(listAdapter);

                // when finished, show view
                view.setVisibility(View.VISIBLE);
            }
        }, product_id);
        queue.add(request);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
