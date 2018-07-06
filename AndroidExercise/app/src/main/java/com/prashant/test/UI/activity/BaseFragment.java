package com.prashant.test.UI.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.prashant.test.R;
import com.prashant.test.business.DataObject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseFragment extends Fragment {

    @BindView(R.id.content_image)
    ImageView mImageView;
    @BindView(R.id.content_description)
    TextView mDescription;
    @BindView(R.id.image_title)
    TextView mTitle;

    private DataObject mData;

    public static BaseFragment newInstance() {
        return new BaseFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRoot = inflater.inflate(R.layout.fragment_content_details, container, false);
        ButterKnife.bind(this, mRoot);
        mData = (DataObject) getArguments().getSerializable(DataObject.class.getSimpleName());
        return mRoot;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mData.getmImage()!= null)
        {
            Picasso.with(getContext()).load(mData.getmImage()).into(mImageView);
        }else
        {
            Picasso.with(getContext()).load(R.mipmap.ic_launcher_round).into(mImageView);
        }

        mTitle.setText(mData.getmName());
        mDescription.setText(mData.getmDescription());

    }

}
