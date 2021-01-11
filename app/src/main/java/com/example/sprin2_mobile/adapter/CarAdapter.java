package com.example.sprin2_mobile.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sprin2_mobile.R;
import com.example.sprin2_mobile.activity.CarDetailActivity;
import com.example.sprin2_mobile.activity.CarList;
import com.example.sprin2_mobile.entity.Car;

import java.util.List;

public class CarAdapter extends BaseAdapter {
    private List<Car> carList;
    private Activity activity;

    public CarAdapter(List<Car> carList, Activity activity) {
        this.carList = carList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return this.carList.size();
    }

    @Override
    public Object getItem(int position) {
        return carList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        convertView = layoutInflater.inflate(R.layout.car_item, null);
        TextView carName = convertView.findViewById(R.id.carName);
        carName.setText((position + 1) + ": " + carList.get(position).getLicensePlate());
        return convertView;
    }
}
