package com.project.boostcamp.staffdinnerrestraurant.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.project.boostcamp.publiclibrary.data.Apply;
import com.project.boostcamp.publiclibrary.util.GeocoderHelper;
import com.project.boostcamp.publiclibrary.util.MarkerBuilder;
import com.project.boostcamp.staffdinnerrestraurant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApplyDetailActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {
    private Apply apply;
    @BindView(R.id.tool_bar) Toolbar toolbar;
    @BindView(R.id.text_name) TextView textName;
    @BindView(R.id.text_message) TextView textMessage;
    @BindView(R.id.text_number) TextView textNumber;
    @BindView(R.id.text_wanted_time) TextView textWantedTime;
    @BindView(R.id.text_wanted_style) TextView textWantedStyle;
    @BindView(R.id.text_wanted_menu) TextView textWantedMenu;
    @BindView(R.id.text_location) TextView textLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_detail);
        ButterKnife.bind(this);

        if(getIntent() != null) {
            apply = getIntent().getParcelableExtra(Apply.class.getName());
            setupToolbar();
            setupView();
            setupMap();
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.apply_detail_activity_title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
    }

    private void setupView() {
        textName.setText(getString(R.string.text_apply_detail_name, apply.getWriterName()));
        textMessage.setText(apply.getTitle());
        textNumber.setText(getString(R.string.text_apply_detail_number, apply.getNumber()));
        textWantedTime.setText(apply.getWantedTime() + "");
        textWantedStyle.setText(apply.getWantedStyle());
        textWantedMenu.setText(apply.getWantedMenu());
        textLocation.setText(GeocoderHelper.getAddress(
                this
                , new LatLng(
                        apply.getWantedLatitude(),
                        apply.getWantedLongitude())));
    }

    private void setupMap() {
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings settings = googleMap.getUiSettings();
        settings.setZoomControlsEnabled(false);
        settings.setMyLocationButtonEnabled(false);
        settings.setScrollGesturesEnabled(false);
        LatLng latLng = new LatLng(apply.getWantedLatitude(), apply.getWantedLongitude());
        googleMap.addMarker(MarkerBuilder.simple(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
        googleMap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        MapDetailActivity.show(
                this
                , apply.getWantedLatitude()
                , apply.getWantedLongitude()
                , true);
    }

    @OnClick(R.id.button_send)
    public void checkEstimate() {
        // TODO: 2017-07-31 견적서를 이미 작성했는지 확인해야함.
        boolean alreadyWrited = false;
        if(!alreadyWrited) {
            showWriteEstimateActivity();
        }
    }

    private void showWriteEstimateActivity() {
        Intent intent = new Intent(this, WriteEstimateActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
