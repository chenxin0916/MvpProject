package com.awesome.mvp.view;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;

import com.awesome.mvp.utils.OpenglUtils;

import java.util.Collections;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CameraView extends GLSurfaceView implements GLSurfaceView.Renderer {

    private static final String TAG = CameraView.class.getSimpleName();
    private boolean mFlashAvailable = false;
    private String mCameraId = null;
    private CameraDevice mCameraDevice;
    private SurfaceTexture mSurfaceTexture;
    private Surface mSurface;
    private CameraCaptureSession cameracapturesession;


    public CameraView(Context context) {
        this(context, null);
    }

    public CameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEgl();
        initCamra(context);
    }

    private void initEgl() {
        setEGLContextClientVersion(2);
        setRenderer(this);
        int oesTextureObject = OpenglUtils.createOesTextureObject();
    }

    private void initCamra(Context context) {
        CameraManager cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        if (cameraManager == null) {
            Log.e(TAG, "CameraManager is not vailable CameraView");
            return;
        }
        try {
            getCameraId(cameraManager);
            if (mCameraId == null) {
                return;
            }

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                Log.e(TAG, "no Camera permission");
                return;
            }
            cameraManager.openCamera(mCameraId, stateCallback, cameraHandler);
        } catch (Exception e) {
            Log.e(TAG, "start camera failed" + e.toString());
            e.printStackTrace();
        }
    }

    /**
     *
     * 获取cameraId，以及闪光灯是否可用
     */
    private void getCameraId(CameraManager cameraManager) throws CameraAccessException {
        for (String cameraId : cameraManager.getCameraIdList()) {
            CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics(cameraId); //获取相机参数
            Integer facing = cameraCharacteristics.get(CameraCharacteristics.LENS_FACING);

            if (facing != null && facing == CameraCharacteristics.LENS_FACING_FRONT) {
                continue;
            }

            StreamConfigurationMap configurationMap = cameraCharacteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

            if (configurationMap == null) {
                return;
            }

            Boolean avaiable = cameraCharacteristics.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);

            mFlashAvailable = avaiable == null ? false : avaiable;
            mCameraId = cameraId;

        }
    }

    /**
     * camera 状态的一些回掉
     */
    private CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCameraDevice = camera;
            createCameraPreviewSession();

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            camera.close();
            mCameraDevice = null;
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            camera.close();
            mCameraDevice = null;
            Log.e(TAG,error+"");
        }
    };



    private Handler cameraHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    private void createCameraPreviewSession() {
        try {
            initSurface();
            final CaptureRequest.Builder captureRequestBuilder = mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(mSurface);
            mCameraDevice.createCaptureSession(Collections.singletonList(mSurface), new CameraCaptureSession.StateCallback() {
                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {

                    if (null == mCameraDevice){
                        return;
                    }
                    cameracapturesession = session;
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                            CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                            CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                    CaptureRequest build = captureRequestBuilder.build();

                    try {
                        cameracapturesession.setRepeatingRequest(build,null,cameraHandler);
                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {

                }
            },cameraHandler);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        if (mSurfaceTexture ==null){
            return;
        }

        mSurfaceTexture.updateTexImage();
//        mSurfaceTexture.getTransformMatrix();

    }

    public void initSurface(){

        mSurfaceTexture = new SurfaceTexture(OpenglUtils.createOesTextureObject());
        mSurfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
            @Override
            public void onFrameAvailable(SurfaceTexture surfaceTexture) {
                requestRender();
            }
        });
        mSurface = new Surface(mSurfaceTexture);

    }


    public  void  destory(){
        mSurface.release();
        mSurfaceTexture.release();
    }
}
