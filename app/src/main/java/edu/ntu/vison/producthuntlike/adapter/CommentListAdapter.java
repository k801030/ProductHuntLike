package edu.ntu.vison.producthuntlike.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ntu.vison.producthuntlike.R;
import edu.ntu.vison.producthuntlike.model.Comment;

/**
 * Created by Vison on 2015/6/29.
 */
public class CommentListAdapter extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<Comment> commentGroupList;
    private HashMap<Integer, ArrayList<Comment>> commentChildList;

    public CommentListAdapter(Context context, ArrayList<Comment> commentGroupList, HashMap<Integer,ArrayList<Comment>> commentChildList) {
        this.context = context;
        this.commentGroupList = commentGroupList;
        this.commentChildList = commentChildList;
    }



    @Override
    public Comment getGroup(int groupPosition) {
        return commentGroupList.get(groupPosition);
    }

    @Override
    public Comment getChild(int groupPosition, int childPosition) {
        return commentChildList.get(groupPosition).get(childPosition);
    }

    @Override
    public int getGroupCount() {
        return commentGroupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return commentChildList.get(groupPosition).size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            view = inflater.inflate(R.layout.adpater_comment_list_group, null);
        }
        // get components
        ImageView userImage = (ImageView) view.findViewById(R.id.user_image);
        TextView userName = (TextView) view.findViewById(R.id.user_name);
        TextView commentBody = (TextView) view.findViewById(R.id.comment_body);

        // set components
        UrlImageViewHelper.setUrlDrawable(userImage, getGroup(groupPosition).getUser().getImageUrl().getPx50());
        userName.setText(getGroup(groupPosition).getUser().getName());
        commentBody.setText(getGroup(groupPosition).getBody());
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            view = inflater.inflate(R.layout.adapter_comment_list_child, null);
        }
        // get components
        ImageView userImage = (ImageView) view.findViewById(R.id.user_image);
        TextView userName = (TextView) view.findViewById(R.id.user_name);
        TextView commentBody = (TextView) view.findViewById(R.id.comment_body);

        // set components
        UrlImageViewHelper.setUrlDrawable(userImage, getChild(groupPosition, childPosition).getUser().getImageUrl().getPx50());
        TextView textView = (TextView) view.findViewById(R.id.comment_body);
        textView.setText(getChild(groupPosition, childPosition).getBody());
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
