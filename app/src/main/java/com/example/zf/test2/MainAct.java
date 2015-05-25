package com.example.zf.test2;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by zf on 2015/3/5.
 */
public class MainAct extends Activity {

    PinnedSectionListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (PinnedSectionListView) this.findViewById(R.id.listview);

        listView.setAdapter(new MyAdapter());
    }


    public class MyAdapter extends BaseAdapter implements PinnedSectionListView.PinnedSectionListAdapter {

        private final static int viewType_item = 0;
        private final static int viewType_section = 1;

        public MyAdapter() {


        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {

            if (position % 5 == 0) {
                return viewType_section;
            }
            return viewType_item;
        }


        @Override
        public boolean isItemViewTypePinned(int viewType) {
            System.out.println("viewType : " + viewType);
            return viewType == viewType_section;
        }

        @Override
        public int getCount() {
            return 60;
        }


        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            int viewType = getItemViewType(position);
            View view = null;
            switch (viewType) {
                case viewType_item:

                    view = getItemView(position, convertView, parent);
                    break;

                case viewType_section:
                    view = getSectionView(position, convertView, parent);
                    break;
            }
            return view;
        }

        private View getItemView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.i, parent,false);
            }

            TextView tv = (TextView) convertView.findViewById(R.id.textview);
            tv.setText("i : " + position);

            return convertView;
        }

        private View getSectionView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.s, parent,false);
            }

            TextView tv = (TextView) convertView.findViewById(R.id.textview);
            tv.setText("s : " + position);

            return convertView;
        }
    }
}
