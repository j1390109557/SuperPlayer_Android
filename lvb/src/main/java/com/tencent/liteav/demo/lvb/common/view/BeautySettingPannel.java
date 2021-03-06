package com.tencent.liteav.demo.lvb.common.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.liteav.demo.lvb.R;
import com.tencent.liteav.demo.lvb.common.utils.VideoMaterialDownloadProgress;

import java.util.ArrayList;
import java.util.List;

public class BeautySettingPannel extends FrameLayout implements SeekBar.OnSeekBarChangeListener {
    private final String TAG = "BeautySettingPannel";
    //    public static final int ITEM_TYPE_BEAUTY_STYLE = 0;
    public static final int ITEM_TYPE_BEAUTY = 0;
    public static final int ITEM_TYPE_FILTTER = 1;
    public static final int ITEM_TYPE_MOTION = 2;
    public static final int ITEM_TYPE_KOUBEI = 3;
    public static final int ITEM_TYPE_GREEN = 4;
//    public static final int ITEM_TYPE_BEAUTY_BODY = 5; // 美体
    public static final int ITEM_TYPE_BEAUTY_FACE = 5; // 美妆
    public static final int ITEM_TYPE_GESUTRE = 6;     // 手势

    private int mSencodGradleType = ITEM_TYPE_BEAUTY;
    private ArrayList<String> mFirstGradleArrayString = new ArrayList<String>();
    private ArrayList<String> mSencodeGradleArrayString = new ArrayList<String>();
    private int mThirdGradleIndex = 0;
    private int[][] mSzSeekBarValue = null;
    private int[] mSzSecondGradleIndex = new int[16];

    public static final int BEAUTYPARAM_EXPOSURE = 0;
    public static final int BEAUTYPARAM_BEAUTY = 1;
    public static final int BEAUTYPARAM_WHITE = 2;
    public static final int BEAUTYPARAM_FACE_SLIM = 3;
    public static final int BEAUTYPARAM_BIG_EYE = 4;
    public static final int BEAUTYPARAM_FILTER = 5;
    public static final int BEAUTYPARAM_FILTER_MIX_LEVEL = 6;
    public static final int BEAUTYPARAM_MOTION_TMPL = 7;
    public static final int BEAUTYPARAM_GREEN = 8;
    //    public static final int BEAUTYPARAM_BEAUTY_STYLE = 9;
    public static final int BEAUTYPARAM_RUDDY = 10;
    public static final int BEAUTYPARAM_NOSESCALE = 11;
    public static final int BEAUTYPARAM_CHINSLIME = 12;
    public static final int BEAUTYPARAM_FACEV = 13;
    public static final int BEAUTYPARAM_FACESHORT = 14;
    public static final int BEAUTYPARAM_SHARPEN = 15;
    public static final int BEAUTYPARAM_CAPTURE_MODE = 16;
    public static final int BEAUTYPARAM_SKINBEAUTY = 17;
    public static final int BEAUTYPARAM_EYELIGHTEN = 18;
    public static final int BEAUTYPARAM_TOOTHWHITEN = 19;
    public static final int BEAUTYPARAM_WRINKLEREMOVE = 20;
    public static final int BEAUTYPARAM_POUNCHREMOVE = 21;
    public static final int BEAUTYPARAM_SMILELINESREMOVE = 22;
    public static final int BEAUTYPARAM_FOREHEAD = 23;
    public static final int BEAUTYPARAM_EYEDISTANCE = 24;
    public static final int BEAUTYPARAM_EYEANGLE = 25;
    public static final int BEAUTYPARAM_MOUTHSHAPE = 26;
    public static final int BEAUTYPARAM_NOSEWING = 27;
    public static final int BEAUTYPARAM_NOSEPOSITION = 28;
    public static final int BEAUTYPARAM_LIPSTHICKNESS = 29;
    public static final int BEAUTYPARAM_FACEBEAUTY = 30;
    public static final int BEAUTYPARAM_LONGLEG = 31;
    public static final int BEAUTYPARAM_THINWAIST = 32;
    public static final int BEAUTYPARAM_THINBODY = 33;
    public static final int BEAUTYPARAM_THINSHOULDER = 34;

    static public class BeautyParams {
        public static final int BEAUTYPARAM_BEAUTY_STYLE_SMOOTH = 0; // 光滑
        public static final int BEAUTYPARAM_BEAUTY_STYLE_NATURAL = 1; // 自然
        public static final int BEAUTYPARAM_BEAUTY_STYLE_HAZY = 2; // 天天P图(朦胧)

        public float mExposure = 0;
        public int mBeautyLevel = 4;
        public int mWhiteLevel = 1;
        public int mRuddyLevel = 0;
        public int mSharpenLevel = 3;
        public int mBeautyStyle = 0;
        public int mFilterMixLevel = 5;
        public int mBigEyeLevel;
        public int mFaceSlimLevel;
        public int mNoseScaleLevel;
        public int mChinSlimLevel;
        public int mFaceVLevel;
        public int mFaceShortLevel;

        public int mEyeLightenLevel = 0;                 // 亮眼
        public int mToothWhitenLevel = 0;                // 白牙
        public int mWrinkleRemoveLevel = 0;              // 祛皱
        public int mPounchRemoveLevel = 0;               // 祛眼袋
        public int mSmileLinesRemoveLevel = 0;             // 去法令纹
        public int mForeheadLevel = 0;                   // 发际线
        public int mEyeDistanceLevel = 0;                // 眼距
        public int mEyeAngleLevel = 0;                   // 眼角
        public int mMouthShapeLevel = 0;                 // 嘴型
        public int mNoseWingLevel = 0;                   // 鼻翼
        public int mNosePositionLevel = 0;               // 鼻子位置
        public int mLipsThicknessLevel = 0;              // 嘴唇厚度
        public int mFaceBeautyLevel = 0;                 // 脸型

        public int mLongLegLevel = 0;                    // 长腿
        public int mThinWaistLevel = 0;                  // 瘦腰
        public int mThinBodyLevel = 0;                   // 瘦体
        public int mThinShoulderLevel = 0;               // 瘦肩

        public int filterIndex;
        public Bitmap mFilterBmp;
        public String mMotionTmplPath;
        public String mGreenFile;
        public int mCaptureMode = 0;
    }

    private String[] mFirstGradleString = {
//            "风格",
            "美颜",
            "滤镜",
            "动效",
            "抠背",
            "绿幕",
//            "美体",
            "美妆",
            "手势"
    };

//    private String[] mBeautyBodyString = {
//            "长腿",
//            "瘦腰",
//            "瘦体",
//            "瘦肩",
//    };

    private String[] mBeautyFaceString = {
            "无",
            "原宿复古"
    };

    private String[] mGestureString = {
            "无",
            "皮卡丘"
    };

    private String[] mBeautyString = {
            "美颜(光滑)",
            "美颜(自然)",
            "美颜(天天P图)",
            "美白",
            "红润",
//            "清晰",
            "曝光",
            "大眼",
            "瘦脸",
            "V脸",
            "下巴",
            "短脸",
            "小鼻",
            "亮眼",
            "白牙",
            "祛眼袋",
            "祛皱",
            "祛法令纹",
            "发际线",
            "眼距",
            "眼角",
            "嘴型",
            "鼻翼",
            "鼻子位置",
            "嘴唇厚度",
            "脸型",
    };
    private String[] mBeautyFilterTypeString = {
            "无",
            "标准",    // 4
            "樱红",    // 8
            "云裳",    // 8
            "纯真",    // 8
            "白兰",    // 10
            "元气",    // 8
            "超脱",    // 10
            "香氛",    // 5
            "美白",    // 5
            "浪漫",    // 5
            "清新",    // 5
            "唯美",    // 5
            "粉嫩",    // 5
            "怀旧",    // 5
            "蓝调",    // 5
            "清凉",    // 5
            "日系",    // 5
    };
    private String[] mMotionTypeString = {
            "无动效",
            "Boom",
            "霓虹鼠",
            "星耳",
            "疯狂打call",
            "Q星座",
            "彩色丝带",
            "刘海发带",
            "变脸",
            "紫色小猫",
            "花仙子",
            "小公举",
    };
    private String[] mGreenString = {
            "无",
            "Good Luck"
    };
    private String[] mKoubeiString = {
            "无",
            "AI抠背"
    };

//    private String[] mCaptureModeString = {
//            "低采",
//            "高采"
//    };

    private List<MotionData> motionDataList = new ArrayList<>();
    private List<MotionData> motionDataKoubeiList = new ArrayList<>();
    private List<MotionData> motionBeautyFaceList = new ArrayList<>();
    private List<MotionData> motionGestureList = new ArrayList<>();
    private MotionData mMotionData;

    private SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());


    private void initMotionData() {
        motionDataList.add(new MotionData("none", "无动效", "", ""));        // 0
        motionDataList.add(new MotionData("video_boom", "Boom", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_boomAndroid.zip",
                mPrefs.getString("video_boom", "")));                       // 1
        motionDataList.add(new MotionData("video_nihongshu", "霓虹鼠", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_nihongshuAndroid.zip",
                mPrefs.getString("video_nihongshu", "")));                  // 2
        motionDataList.add(new MotionData("video_starear", "星耳", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_starearAndroid.zip",
                mPrefs.getString("video_starear", "")));  // 3
        motionDataList.add(new MotionData("video_fengkuangdacall", "疯狂打call", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_fengkuangdacallAndroid.zip",
                mPrefs.getString("video_fengkuangdacall", "")));            // 4
        motionDataList.add(new MotionData("video_Qxingzuo", "Q星座", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_QxingzuoAndroid.zip",
                mPrefs.getString("video_Qxingzuo", "")));                   // 5
        motionDataList.add(new MotionData("video_caidai", "彩色丝带", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_caidaiAndroid.zip",
                mPrefs.getString("video_caidai", "")));                     // 6
        motionDataList.add(new MotionData("video_liuhaifadai", "刘海发带", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_liuhaifadaiAndroid.zip",
                mPrefs.getString("video_liuhaifadai", "")));                // 7
        motionDataList.add(new MotionData("video_lianpu", "变脸", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_lianpuAndroid.zip",
                mPrefs.getString("video_lianpu", "")));                    // 8
        motionDataList.add(new MotionData("video_purplecat", "紫色小猫", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_purplecatAndroid.zip",
                mPrefs.getString("video_purplecat", "")));                  // 9
        motionDataList.add(new MotionData("video_huaxianzi", "花仙子", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_huaxianziAndroid.zip",
                mPrefs.getString("video_huaxianzi", "")));                  // 10
        motionDataList.add(new MotionData("video_baby_agetest", "小公举", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_baby_agetestAndroid.zip",
                mPrefs.getString("video_baby_agetest", "")));               // 11
        // 单独把 抠背 的动效拿出来
        motionDataKoubeiList.add(new MotionData("none", "无", "", ""));        // 0
        motionDataKoubeiList.add(new MotionData("video_xiaofu", "校服", "https://dldir1.qq.com/hudongzhibo/AISpecial/Android/170/video_xiaofuAndroid.zip",
                mPrefs.getString("video_xiaofu", "")));

        // 美妆
        motionBeautyFaceList.add(new MotionData("none", "无", "", ""));
        motionBeautyFaceList.add(new MotionData("video_yuansufugu", "原宿复古", "http://dldir1.qq.com/hudongzhibo/AISpecial/Android/180/video_yuansufugu.zip",
                mPrefs.getString("video_yuansufugu", "")));
        // 手势
        motionGestureList.add(new MotionData("none", "无", "", ""));
        motionGestureList.add(new MotionData("video_pikachu", "皮卡丘", "http://dldir1.qq.com/hudongzhibo/AISpecial/Android/181/video_pikachu.zip",
                mPrefs.getString("video_pikachu", "")));
    }

    class MotionData {
        public MotionData(String motionId, String motionName, String motionUrl, String motionPath) {
            this.motionId = motionId;
            this.motionName = motionName;
            this.motionUrl = motionUrl;
            this.motionPath = motionPath;
        }

        public String motionId;
        public String motionName;
        public String motionUrl;
        public String motionPath;
    }

    public interface IOnBeautyParamsChangeListener {
        void onBeautyParamsChange(BeautyParams params, int key);
    }

    // 新界面
    TXHorizontalPickerView mFirstGradlePicker;
    ArrayAdapter<String> mFirstGradleAdapter;
    private final int mFilterBasicLevel = 5;

    private final int mBeautyBasicLevel = 4;
    private final int mWhiteBasicLevel = 1;
    private final int mRuddyBasicLevel = 0;

    private int mExposureLevel = -1;
    private final int mSharpenLevel = 3;

    TXHorizontalPickerView mSecondGradlePicker;
    ArrayAdapter<String> mSecondGradleAdapter;

    LinearLayout mSeekBarLL = null;
    TextView mSeekBarValue = null;
    CustomProgressDialog mCustomProgressDialog;

    private SeekBar mThirdGradleSeekBar;

    private Context mContext;

    private IOnBeautyParamsChangeListener mBeautyChangeListener;

    public BeautySettingPannel(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.beauty_pannel, this);
        mContext = context;
        initView(view);
    }

    public void setBeautyParamsChangeListener(IOnBeautyParamsChangeListener listener) {
        mBeautyChangeListener = listener;
    }

    public void disableExposure() {
        mBeautyString = new String[]{
                "美颜(光滑)",
                "美颜(自然)",
                "美颜(天天P图)",
                "美白",
                "红润",
                "大眼",
                "瘦脸",
                "V脸",
                "下巴",
                "短脸",
                "小鼻",
                "亮眼",
                "白牙",
                "祛眼袋",
                "祛皱",
                "祛法令纹",
                "发际线",
                "眼距",
                "眼角",
                "嘴型",
                "鼻翼",
                "鼻子位置",
                "嘴唇厚度",
        };
        setFirstPickerType(null);
    }

    private void initView(View view) {
        mThirdGradleSeekBar = (SeekBar) view.findViewById(R.id.ThirdGradle_seekbar);
        mThirdGradleSeekBar.setOnSeekBarChangeListener(this);

        mFirstGradlePicker = (TXHorizontalPickerView) view.findViewById(R.id.FirstGradePicker);
        mSecondGradlePicker = (TXHorizontalPickerView) view.findViewById(R.id.secondGradePicker);

        mSeekBarLL = (LinearLayout) view.findViewById(R.id.layoutSeekBar);

        mSeekBarValue = (TextView) view.findViewById(R.id.TextSeekBarValue);

        setFirstPickerType(view);

        initMotionData();
    }

    private void setFirstPickerType(View view) {
        mFirstGradleArrayString.clear();
        for (int i = 0; i < mFirstGradleString.length; i++) {
            mFirstGradleArrayString.add(mFirstGradleString[i]);
        }
        mFirstGradleAdapter = new ArrayAdapter<String>(mContext, 0, mFirstGradleArrayString) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                String value = getItem(position);
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
                }
                TextView view = (TextView) convertView.findViewById(android.R.id.text1);
                view.setTag(position);
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                view.setText(value);
                view.setPadding(15, 5, 30, 5);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int index = (int) view.getTag();
                        ViewGroup group = (ViewGroup) mFirstGradlePicker.getChildAt(0);
                        for (int i = 0; i < mFirstGradleAdapter.getCount(); i++) {
                            View v = group.getChildAt(i);
                            if (v instanceof TextView) {
                                if (i == index) {
                                    ((TextView) v).setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                                } else {
                                    ((TextView) v).setTextColor(Color.WHITE);
                                }
                            }
                        }
                        setSecondPickerType(index);
                    }
                });
                return convertView;

            }
        };
        mFirstGradlePicker.setAdapter(mFirstGradleAdapter);
        mFirstGradlePicker.setClicked(ITEM_TYPE_BEAUTY);
    }


    private void setSecondPickerType(int type) {
        mSencodeGradleArrayString.clear();
        mSencodGradleType = type;

        String[] typeString = null;
        switch (type) {
//            case ITEM_TYPE_BEAUTY_STYLE:
//                typeString = mBeautyStyleString;
//                break;
            case ITEM_TYPE_BEAUTY:
                typeString = mBeautyString;
                break;
            case ITEM_TYPE_FILTTER:
                typeString = mBeautyFilterTypeString;
                break;
            case ITEM_TYPE_MOTION:
                typeString = mMotionTypeString;
                break;
            case ITEM_TYPE_KOUBEI:
                typeString = mKoubeiString;
                break;
            case ITEM_TYPE_GREEN:
                typeString = mGreenString;
                break;
//            case ITEM_TYPE_BEAUTY_BODY:
//                typeString = mBeautyBodyString;
//                break;
            case ITEM_TYPE_BEAUTY_FACE:
                typeString = mBeautyFaceString;
                break;
            case ITEM_TYPE_GESUTRE:
                typeString = mGestureString;
                break;
            default:
                break;
        }
        for (int i = 0; i < typeString.length; i++) {
            mSencodeGradleArrayString.add(typeString[i]);
        }
        mSecondGradleAdapter = new ArrayAdapter<String>(mContext, 0, mSencodeGradleArrayString) {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                String value = getItem(position);
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
                }
                TextView view = (TextView) convertView.findViewById(android.R.id.text1);
                view.setTag(position);
                view.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                view.setText(value);
                view.setPadding(15, 5, 30, 5);
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final int index = (int) view.getTag();
                        ViewGroup group = (ViewGroup) mSecondGradlePicker.getChildAt(0);
                        for (int i = 0; i < mSecondGradleAdapter.getCount(); i++) {
                            View v = group.getChildAt(i);
                            if (v instanceof TextView) {
                                if (i == index) {
                                    ((TextView) v).setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                                } else {
                                    ((TextView) v).setTextColor(Color.WHITE);
                                }
                            }
                        }
                        if (mSencodGradleType != ITEM_TYPE_MOTION && mSencodGradleType != ITEM_TYPE_KOUBEI
                                && mSencodGradleType != ITEM_TYPE_BEAUTY_FACE && mSencodGradleType != ITEM_TYPE_GESUTRE) {
                            // 除了上述类型的几种没有进度条调节
                            setPickerEffect(mSencodGradleType, index);
                        } else {
                            if (mSencodGradleType == ITEM_TYPE_MOTION) {
                                mMotionData = motionDataList.get(position);
                            } else if (mSencodGradleType == ITEM_TYPE_KOUBEI) {
                                mMotionData = motionDataKoubeiList.get(position);
                            } else if (mSencodGradleType == ITEM_TYPE_BEAUTY_FACE) {
                                mMotionData = motionBeautyFaceList.get(position);
                            } else if (mSencodGradleType == ITEM_TYPE_GESUTRE) {
                                mMotionData = motionGestureList.get(position);
                            }

                            if (mMotionData.motionId.equals("none") || !TextUtils.isEmpty(mMotionData.motionPath)) {
                                setPickerEffect(mSencodGradleType, index);
                            } else if (TextUtils.isEmpty(mMotionData.motionPath)) {
                                VideoMaterialDownloadProgress videoMaterialDownloadProgress = new VideoMaterialDownloadProgress(mContext, mMotionTypeString[position], mMotionData.motionUrl);
                                videoMaterialDownloadProgress.start(new VideoMaterialDownloadProgress.Downloadlistener() {
                                    @Override
                                    public void onDownloadFail(final String errorMsg) {
                                        ((Activity) mContext).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (mCustomProgressDialog != null) {
                                                    mCustomProgressDialog.dismiss();
                                                }
                                                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onDownloadProgress(final int progress) {
                                        ((Activity) mContext).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Log.i(TAG, "onDownloadProgress, progress = " + progress);
                                                if (mCustomProgressDialog == null) {
                                                    mCustomProgressDialog = new CustomProgressDialog();
                                                    mCustomProgressDialog.createLoadingDialog(mContext, "");
                                                    mCustomProgressDialog.setCancelable(false); // 设置是否可以通过点击Back键取消
                                                    mCustomProgressDialog.setCanceledOnTouchOutside(false); // 设置在点击Dialog外是否取消Dialog进度条
                                                    mCustomProgressDialog.show();
                                                }
                                                mCustomProgressDialog.setMsg(progress + "%");
                                            }
                                        });
                                    }

                                    @Override
                                    public void onDownloadSuccess(String filePath) {
                                        mMotionData.motionPath = filePath;
                                        mPrefs.edit().putString(mMotionData.motionId, filePath).apply();
                                        ((Activity) mContext).runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (mCustomProgressDialog != null) {
                                                    mCustomProgressDialog.dismiss();
                                                    mCustomProgressDialog = null;
                                                }
                                                setPickerEffect(mSencodGradleType, index);
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    }
                });
                return convertView;
            }
        };
        mSecondGradlePicker.setAdapter(mSecondGradleAdapter);
        mSecondGradlePicker.setClicked(mSzSecondGradleIndex[mSencodGradleType]);
    }

    private void setPickerEffect(int type, int index) {
        initSeekBarValue();
        mSzSecondGradleIndex[type] = index;
        mThirdGradleIndex = index;

        switch (type) {
//            case ITEM_TYPE_BEAUTY_STYLE:
//                mThirdGradleSeekBar.setVisibility(View.GONE);
//                mSeekBarValue.setVisibility(View.GONE);
//                setBeautyStyle(index);
//                break;
            case ITEM_TYPE_BEAUTY:
//            case ITEM_TYPE_BEAUTY_BODY:// 美体
                mThirdGradleSeekBar.setVisibility(View.VISIBLE);
                mSeekBarValue.setVisibility(View.VISIBLE);
                mThirdGradleSeekBar.setProgress(mSzSeekBarValue[type][index]);
                setBeautyStyle(index, mSzSeekBarValue[type][index]);
                break;
            case ITEM_TYPE_FILTTER:
                setFilter(index);
                mThirdGradleSeekBar.setVisibility(View.VISIBLE);
                mSeekBarValue.setVisibility(View.VISIBLE);
                mThirdGradleSeekBar.setProgress(mSzSeekBarValue[type][index]);
                break;
            case ITEM_TYPE_MOTION:
                mThirdGradleSeekBar.setVisibility(View.GONE);
                mSeekBarValue.setVisibility(View.GONE);
                setDynamicEffect(type, index);
                break;
            case ITEM_TYPE_KOUBEI:
            case ITEM_TYPE_BEAUTY_FACE: // 美妆
            case ITEM_TYPE_GESUTRE:     // 手势
                mThirdGradleSeekBar.setVisibility(View.GONE);
                mSeekBarValue.setVisibility(View.GONE);
                setDynamicEffect(type, index);
                break;
            case ITEM_TYPE_GREEN:
                mThirdGradleSeekBar.setVisibility(View.GONE);
                mSeekBarValue.setVisibility(View.GONE);
                setGreenScreen(index);
                break;
//            case ITEM_TYPE_CAPTURE:
//                mThirdGradleSeekBar.setVisibility(View.GONE);
//                mSeekBarValue.setVisibility(View.GONE);
//                setCaptureMode(index);
//                break;
            default:
                break;
        }

    }

    public void initProgressValue(int type, int index, int progress) {
        switch (type) {
            case ITEM_TYPE_BEAUTY:
            case ITEM_TYPE_FILTTER:
                mSzSeekBarValue[type][index] = progress;
                setPickerEffect(type, index);
                // 复位
                setPickerEffect(type, 0);
                break;
        }
    }

    private Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }


    public Bitmap getFilterBitmapByIndex(int index) {
        Bitmap bmp = null;
        switch (index) {
            case 1:
                bmp = decodeResource(getResources(), R.drawable.filter_biaozhun);
                break;
            case 2:
                bmp = decodeResource(getResources(), R.drawable.filter_yinghong);
                break;
            case 3:
                bmp = decodeResource(getResources(), R.drawable.filter_yunshang);
                break;
            case 4:
                bmp = decodeResource(getResources(), R.drawable.filter_chunzhen);
                break;
            case 5:
                bmp = decodeResource(getResources(), R.drawable.filter_bailan);
                break;
            case 6:
                bmp = decodeResource(getResources(), R.drawable.filter_yuanqi);
                break;
            case 7:
                bmp = decodeResource(getResources(), R.drawable.filter_chaotuo);
                break;
            case 8:
                bmp = decodeResource(getResources(), R.drawable.filter_xiangfen);
                break;
            case 9:
                bmp = decodeResource(getResources(), R.drawable.filter_white);
                break;
            case 10:
                bmp = decodeResource(getResources(), R.drawable.filter_langman);
                break;
            case 11:
                bmp = decodeResource(getResources(), R.drawable.filter_qingxin);
                break;
            case 12:
                bmp = decodeResource(getResources(), R.drawable.filter_weimei);
                break;
            case 13:
                bmp = decodeResource(getResources(), R.drawable.filter_fennen);
                break;
            case 14:
                bmp = decodeResource(getResources(), R.drawable.filter_huaijiu);
                break;
            case 15:
                bmp = decodeResource(getResources(), R.drawable.filter_landiao);
                break;
            case 16:
                bmp = decodeResource(getResources(), R.drawable.filter_qingliang);
                break;
            case 17:
                bmp = decodeResource(getResources(), R.drawable.filter_rixi);
                break;
            default:
                bmp = null;
                break;
        }
        return bmp;
    }

    public int getFilterProgress(int index) {
        return mSzSeekBarValue[ITEM_TYPE_FILTTER][index];
    }

    //设置滤镜
    private void setFilter(int index) {
        Bitmap bmp = getFilterBitmapByIndex(index);
        if (mBeautyChangeListener != null) {
            BeautyParams params = new BeautyParams();
            params.mFilterBmp = bmp;
            params.filterIndex = index;
            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_FILTER);
        }
    }


    public void setCurrentFilterIndex(int index) {
        mSzSecondGradleIndex[ITEM_TYPE_FILTTER] = index;
        if (mSencodGradleType == ITEM_TYPE_FILTTER) { //当前就是这个Type
            ViewGroup group = (ViewGroup) mSecondGradlePicker.getChildAt(0);
            for (int i = 0; i < mSecondGradleAdapter.getCount(); i++) {
                View v = group.getChildAt(i);
                if (v instanceof TextView) {
                    if (i == index) {
                        ((TextView) v).setTextColor(mContext.getResources().getColor(R.color.colorAccent));
                    } else {
                        ((TextView) v).setTextColor(Color.WHITE);
                    }
                }
            }

            mThirdGradleIndex = index;
            mThirdGradleSeekBar.setVisibility(View.VISIBLE);
            mSeekBarValue.setVisibility(View.VISIBLE);
            mThirdGradleSeekBar.setProgress(mSzSeekBarValue[ITEM_TYPE_FILTTER][index]);
        }
    }

    //切换采集模式
    private void setCaptureMode(int index) {
        if (mBeautyChangeListener != null) {
            BeautyParams params = new BeautyParams();
            params.mCaptureMode = index;
            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_CAPTURE_MODE);
        }
    }

    //设置绿幕
    private void setGreenScreen(int index) {
        String file = "";
        switch (index) {
            case 1:
                file = "green_1.mp4";
                break;
            default:
                break;
        }
        if (mBeautyChangeListener != null) {
            BeautyParams params = new BeautyParams();
            params.mGreenFile = file;
            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_GREEN);
        }
    }

    //设置动效
    private void setDynamicEffect(int type, int index) {
        MotionData motionData = null;
        if (type == ITEM_TYPE_MOTION) {
            motionData = motionDataList.get(index);
        } else if (type == ITEM_TYPE_KOUBEI) {
            motionData = motionDataKoubeiList.get(index);
        } else if (type == ITEM_TYPE_BEAUTY_FACE) {
            motionData = motionBeautyFaceList.get(index);
        } else if (type == ITEM_TYPE_GESUTRE) {
            motionData = motionGestureList.get(index);
            if (motionData.motionId.equals("video_pikachu")) {
                Toast.makeText(mContext, "伸出手掌", Toast.LENGTH_SHORT).show();
            }
        }
        String path = motionData.motionPath;
        if (mBeautyChangeListener != null) {
            BeautyParams params = new BeautyParams();
            params.mMotionTmplPath = path;
            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_MOTION_TMPL);
        }
    }

    // 设置美颜类型
    private void setBeautyStyle(int index, int beautyLevel) {
        int style = index;
        if (index >= 3) {
            return;
        }
        if (mBeautyChangeListener != null) {
            BeautyParams params = new BeautyParams();
            params.mBeautyStyle = style;
            params.mBeautyLevel = beautyLevel;
            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_BEAUTY);
        }
    }

    public void setViewVisibility(int id, int visible) {
        LinearLayout contentLayout = (LinearLayout) getChildAt(0);
        int count = contentLayout.getChildCount();
        for (int i = 0; i < count; ++i) {
            View view = contentLayout.getChildAt(i);
            if (view.getId() == id) {
                view.setVisibility(visible);
                return;
            }
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        initSeekBarValue();
        mSzSeekBarValue[mSencodGradleType][mThirdGradleIndex] = progress;   // 记录设置的值
        mSeekBarValue.setText(String.valueOf(progress));

        if (seekBar.getId() == R.id.ThirdGradle_seekbar) {
            if (mSencodGradleType == ITEM_TYPE_BEAUTY) {
                switch (mSencodeGradleArrayString.get(mThirdGradleIndex)) {
                    case "美颜(光滑)":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mBeautyLevel = progress;
                            params.mBeautyStyle = BeautyParams.BEAUTYPARAM_BEAUTY_STYLE_SMOOTH;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_BEAUTY);
                        }
                        break;
                    case "美颜(自然)":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mBeautyLevel = progress;
                            params.mBeautyStyle = BeautyParams.BEAUTYPARAM_BEAUTY_STYLE_NATURAL;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_BEAUTY);
                        }
                        break;
                    case "美颜(天天P图)":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mBeautyLevel = progress;
                            params.mBeautyStyle = BeautyParams.BEAUTYPARAM_BEAUTY_STYLE_HAZY;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_BEAUTY);
                        }
                        break;
                    case "美白":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mWhiteLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_WHITE);
                        }
                        break;
                    case "红润":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mRuddyLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_RUDDY);
                        }
                        break;
                    case "曝光":
                        if (mBeautyChangeListener != null && (0 != progress || mExposureLevel > 0)) {
                            mExposureLevel = progress;
                            BeautyParams params = new BeautyParams();
                            params.mExposure = ((float) progress - 10.0f) / 10.0f;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_EXPOSURE);
                        }
                        break;
                    case "大眼":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mBigEyeLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_BIG_EYE);
                        }
                        break;
                    case "瘦脸":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mFaceSlimLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_FACE_SLIM);
                        }
                        break;
                    case "V脸":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mFaceVLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_FACEV);
                        }
                        break;
                    case "下巴":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mChinSlimLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_CHINSLIME);
                        }
                        break;
                    case "短脸":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mFaceShortLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_FACESHORT);
                        }
                        break;
                    case "小鼻":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mNoseScaleLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_NOSESCALE);
                        }
                        break;
                    case "亮眼":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mEyeLightenLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_EYELIGHTEN);
                        }
                        break;
                    case "白牙":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mToothWhitenLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_TOOTHWHITEN);
                        }
                        break;
                    case "祛眼袋":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mPounchRemoveLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_POUNCHREMOVE);
                        }
                        break;
                    case "祛皱":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mWrinkleRemoveLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_WRINKLEREMOVE);
                        }
                        break;
                    case "祛法令纹":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mSmileLinesRemoveLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_SMILELINESREMOVE);
                        }
                        break;
                    case "发际线":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mForeheadLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_FOREHEAD);
                        }
                        break;
                    case "眼距":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mEyeDistanceLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_EYEDISTANCE);
                        }
                        break;
                    case "眼角":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mEyeAngleLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_EYEANGLE);
                        }
                        break;
                    case "嘴型":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mMouthShapeLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_MOUTHSHAPE);
                        }
                        break;
                    case "鼻翼":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mNoseWingLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_NOSEWING);
                        }
                        break;
                    case "鼻子位置":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mNosePositionLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_NOSEPOSITION);
                        }
                        break;
                    case "嘴唇厚度":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mLipsThicknessLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_LIPSTHICKNESS);
                        }
                        break;
                    case "脸型":
                        if (mBeautyChangeListener != null) {
                            BeautyParams params = new BeautyParams();
                            params.mFaceBeautyLevel = progress;
                            mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_FACEBEAUTY);
                        }
                        break;
                    default:
                        break;
                }
            } else if (mSencodGradleType == ITEM_TYPE_FILTTER) {
                if (mBeautyChangeListener != null) {
                    BeautyParams params = new BeautyParams();
                    params.mFilterMixLevel = progress;
                    mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_FILTER_MIX_LEVEL);
//                } else if (mSencodGradleType == ITEM_TYPE_BEAUTY_BODY) {
//                    switch (mSencodeGradleArrayString.get(mThirdGradleIndex)) {
//                        case "长腿":
//                            if (mBeautyChangeListener != null) {
//                                BeautyParams params = new BeautyParams();
//                                params.mLongLegLevel = progress;
//                                mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_LONGLEG);
//                            }
//                            break;
//                        case "瘦腰":
//                            if (mBeautyChangeListener != null) {
//                                BeautyParams params = new BeautyParams();
//                                params.mThinWaistLevel = progress;
//                                mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_THINWAIST);
//                            }
//                            break;
//                        case "瘦体":
//                            if (mBeautyChangeListener != null) {
//                                BeautyParams params = new BeautyParams();
//                                params.mThinBodyLevel = progress;
//                                mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_THINBODY);
//                            }
//                            break;
//                        case "瘦肩":
//                            if (mBeautyChangeListener != null) {
//                                BeautyParams params = new BeautyParams();
//                                params.mThinShoulderLevel = progress;
//                                mBeautyChangeListener.onBeautyParamsChange(params, BEAUTYPARAM_THINSHOULDER);
//                            }
//                            break;
//                    }
                }
            }
        }

    }

    private void initSeekBarValue() {
        if (null == mSzSeekBarValue) {
            mSzSeekBarValue = new int[16][32];
            for (int i = 1; i < mSzSeekBarValue[ITEM_TYPE_FILTTER].length; i++) {
                mSzSeekBarValue[ITEM_TYPE_FILTTER][i] = mFilterBasicLevel; // 一般滤镜的推荐值
            }

            // 前八个滤镜的推荐值 （其他默认为5）
            mSzSeekBarValue[ITEM_TYPE_FILTTER][1] = 4;
            mSzSeekBarValue[ITEM_TYPE_FILTTER][2] = 8;
            mSzSeekBarValue[ITEM_TYPE_FILTTER][3] = 8;
            mSzSeekBarValue[ITEM_TYPE_FILTTER][4] = 8;
            mSzSeekBarValue[ITEM_TYPE_FILTTER][5] = 10;
            mSzSeekBarValue[ITEM_TYPE_FILTTER][6] = 8;
            mSzSeekBarValue[ITEM_TYPE_FILTTER][7] = 10;
            mSzSeekBarValue[ITEM_TYPE_FILTTER][8] = 5;


            for (int i = 0; i < mSzSeekBarValue[ITEM_TYPE_BEAUTY].length; i++) {
                if (i >= mSencodeGradleArrayString.size()) {
                    break;
                }
                switch (mSencodeGradleArrayString.get(i)) {
                    case "美颜(光滑)":
                        mSzSeekBarValue[ITEM_TYPE_BEAUTY][i] = mBeautyBasicLevel;
                        break;
                    case "美颜(自然)":
                        mSzSeekBarValue[ITEM_TYPE_BEAUTY][i] = mBeautyBasicLevel;
                        break;
                    case "美颜(天天P图)":
                        mSzSeekBarValue[ITEM_TYPE_BEAUTY][i] = mBeautyBasicLevel;
                        break;
                    case "美白":
                        mSzSeekBarValue[ITEM_TYPE_BEAUTY][i] = mWhiteBasicLevel;
                        break;
                    case "红润":
                        mSzSeekBarValue[ITEM_TYPE_BEAUTY][i] = mRuddyBasicLevel;
                        break;
                    case "曝光":
                        mSzSeekBarValue[ITEM_TYPE_BEAUTY][i] = mExposureLevel;
                        break;
//                    case "清晰":
//                        mSzSeekBarValue[ITEM_TYPE_BEAUTY][i] = mSharpenLevel;
//                        break;

                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    public String[] getBeautyFilterArr() {
        return mBeautyFilterTypeString;
    }

}
