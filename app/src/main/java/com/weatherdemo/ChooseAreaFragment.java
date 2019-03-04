package com.weatherdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weatherdemo.R;
import com.weatherdemo.db.City;
import com.weatherdemo.db.County;
import com.weatherdemo.db.Province;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * 地区选择列表页面
 *
 * @author yejinmo
 */
public class ChooseAreaFragment extends Fragment {

    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;
    private TextView mTxtTitle;
    private Button mBtnBack;
    private ListView mlstArea;
    private ArrayAdapter<String> adapter;
    private ArrayList dataList;
    /**
     * 当前选中的级别
     */
    private int currentLevel;
    /**
     * 省列表
     */
    private List<Province> provinceList;
    /**
     * 市列表
     */
    private List<City> cityList;
    /**
     * 县列表
     */
    private List<County> countyList;
    /**
     * 选中的省份
     */
    private Province selectedProvince;
    /**
     * 选中的城市
     */
    private City selectedCity;
    /**
     * 选中的县
     */
    private County selectedCounty;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_area, container, false);
        mTxtTitle = (TextView) view.findViewById(R.id.title_text);
        mBtnBack = (Button) view.findViewById(R.id.back_button);
        mlstArea = (ListView) view.findViewById(R.id.list_view);
        dataList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, dataList);
        mlstArea.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setonClickListener();
        // 查询省信息
        queryProvinces();

    }

    private void setonClickListener() {
        // 城市列表item点击事件
        mlstArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentLevel == LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    selectedCity = cityList.get(position);
                    querCounties();
                }
            }
        });
        // 返回按钮的点击事件
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLevel == LEVEL_COUNTY) {
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    queryProvinces();
                }
            }
        });
    }

    /**
     * 查询全国所有的省份，优先从数据库查询，如果没有查询到再到服务器上查询
     */
    private void queryProvinces() {
        mTxtTitle.setText(getResources().getString(R.string.country_name));
        mBtnBack.setVisibility(View.GONE);
        provinceList = LitePal.findAll(Province.class);
        if (null != provinceList && provinceList.size() > 0) {
            dataList.clear();
            for (Province province :
                    provinceList) {
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            mlstArea.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            String address = "http://guolin.tech/api/china";
            queryFromServer(address, "province");
        }
    }

    /**
     * 查询选中省内所有的市，优先从数据库查询，如果没有查询到再到服务器上查询
     */
    private void queryCities() {
        mTxtTitle.setText(selectedProvince.getProvinceName());
        mBtnBack.setVisibility(View.GONE);
        cityList = LitePal.where("provinceid = ?", String.valueOf(selectedProvince.getId())).find(City.class);
        if (null != cityList && cityList.size() > 0) {
            dataList.clear();
            for (City city :
                    cityList) {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            mlstArea.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            int porvinceCode = selectedProvince.getProvinceCode();
            String address = "http://guolin.tech/api/china/" + porvinceCode;
            queryFromServer(address, "city");
        }
    }

    /**
     * 查询选中市内所有的县，优先从数据库查询，如果没有查询到再到服务器上查询
     */
    private void querCounties() {
        mTxtTitle.setText(selectedCity.getCityName());
        mBtnBack.setVisibility(View.VISIBLE);
        countyList = LitePal.where("cityid = ?", String.valueOf(selectedCity.getId())).find(County.class);
        if (null != countyList && countyList.size() > 0) {
            dataList.clear();
            for (County county :
                    countyList) {
                dataList.add(county.getCountyName());
            }
            adapter.notifyDataSetChanged();
            mlstArea.setSelection(0);
            currentLevel = LEVEL_COUNTY;
        }
        else
        {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = "http://guolin.tech/api/china/" + provinceCode + "/" + cityCode;
            queryFromServer(address, "county");
        }
    }

    /**
     * 根据传入的地址和类型从服务器上查询省市县数据。
     *
     * @param address
     * @param province
     */
    private void queryFromServer(String address, String province) {

    }
}
