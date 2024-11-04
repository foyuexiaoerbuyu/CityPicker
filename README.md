# CityPicker
城市选择器 三级联动

所有数据均来自公开渠道,涉及隐私请联系删除

![](.\pic\00001.png)

这是个依赖模块直接依赖就行
    private CityPicker mCityPicker = null;
if (cityStr == null) {
            cityStr = CommUtils.openAssetsFile(mBaseActivity, "city.json");
        }
if (mCityPicker == null) {
                    mCityPicker = new CityPicker((Activity) mBaseActivity, mBinding.fraCeBaseInfoLlRoot, cityStr)
                            .setOnCitySelectListener((province, city, county) -> {
//                                  LogUtils.i( "选择区域: " + province + city + county);
                                mBinding.fragCeBaseInfoEdtSheng.setText(province);
                                mBinding.fragCeBaseInfoEdtShi.setText(city);
                                mBinding.fragCeBaseInfoEdtXian.setText(CommUtils.isEmpty(county) ? city : county);
                            });
                }
                mCityPicker.show();
			
/**
 * 打开Assets内的文件
 */
public static String openAssetsFile(Context context, String fileName) {
	String result = "";
	try {
		InputStream is = context.getAssets().open(fileName);
		byte[] buffer = new byte[is.available()];
		is.read(buffer);
		result = new String(buffer, "utf-8");
		is.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return result;
}