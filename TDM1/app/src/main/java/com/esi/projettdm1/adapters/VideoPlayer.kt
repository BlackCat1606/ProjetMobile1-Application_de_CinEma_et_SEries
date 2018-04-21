package com.esi.projettdm1.adapters


import android.content.Context
import android.content.res.AssetFileDescriptor
import android.graphics.Matrix
import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.Log
import android.view.Surface
import android.view.TextureView
import android.view.View

import com.esi.projettdm1.data.PTMovie

import java.io.IOException

import android.content.ContentValues.TAG


 class VideoPlayer:TextureView, TextureView.SurfaceTextureListener {


/**This flag determines that if current VideoPlayer object is first item of the list if it is first item of list */
    internal var isFirstListItem:Boolean = false

internal var isLoaded:Boolean = false
internal var isMpPrepared:Boolean = false
     @get:JvmName("getContext_") lateinit  var  context:Context
private var mVideoHeight:Float = 0.toFloat()
private var mVideoWidth:Float = 0.toFloat()

lateinit var iVideoPreparedListener:IVideoPreparedListener

internal var video:PTMovie = PTMovie("","")
lateinit var url:String
internal var mp:MediaPlayer? = null
lateinit var surface:Surface
internal var s:SurfaceTexture? = null
private var mScaleType:ScaleType? = null

 val currentProgress:Int
get() {
    return if (mp != null) {
        mp!!.currentPosition
    } else 0
}

 enum class ScaleType {
CENTER_CROP, TOP, BOTTOM
}

 interface IVideoPreparedListener {

 fun onVideoPrepared(video:PTMovie)
}

 constructor(context:Context) : super(context) {
this.context = context
}

 constructor(context:Context, attrs:AttributeSet) : super(context, attrs) {}

 fun loadVideo(localPath:String, video:PTMovie) {

this.url = localPath
this.video = video
isLoaded = true

if (this.isAvailable)
{
prepareVideo(surfaceTexture)
}

     surfaceTextureListener = this
}
 fun loadVideo(localPath:String) {

this.url = localPath
this.video = video
isLoaded = true

if (this.isAvailable)
{
prepareVideo(surfaceTexture)
}

     surfaceTextureListener = this
}

override fun onSurfaceTextureAvailable(surface:SurfaceTexture, width:Int, height:Int) {
isMpPrepared = false
prepareVideo(surface)
}

override fun onSurfaceTextureSizeChanged(surface:SurfaceTexture, width:Int, height:Int) {

}

override fun onSurfaceTextureDestroyed(surface:SurfaceTexture):Boolean {

if (mp != null)
{
mp!!.stop()
mp!!.reset()
mp!!.release()
mp = null
}

return false
}

override fun onSurfaceTextureUpdated(surface:SurfaceTexture) {}

 fun setScaleType(scaleType:ScaleType) {
mScaleType = scaleType
}

private fun updateTextureViewSize() {
val viewWidth = width.toFloat()
val viewHeight = height.toFloat()

var scaleX = 1.0f
var scaleY = 1.0f

if (mVideoWidth > viewWidth && mVideoHeight > viewHeight)
{
scaleX = mVideoWidth / viewWidth
scaleY = mVideoHeight / viewHeight
}
else if (mVideoWidth < viewWidth && mVideoHeight < viewHeight)
{
scaleY = viewWidth / mVideoWidth
scaleX = viewHeight / mVideoHeight
}
else if (viewWidth > mVideoWidth)
{
scaleY = (viewWidth / mVideoWidth) / (viewHeight / mVideoHeight)
}
else if (viewHeight > mVideoHeight)
{
scaleX = (viewHeight / mVideoHeight) / (viewWidth / mVideoWidth)
}

 // Calculate pivot points, in our case crop from center
        val pivotPointX:Int
val pivotPointY:Int

when (mScaleType) {
VideoPlayer.ScaleType.TOP -> {
pivotPointX = 0
pivotPointY = 0
}
VideoPlayer.ScaleType.BOTTOM -> {
pivotPointX = (viewWidth).toInt()
pivotPointY = (viewHeight).toInt()
}
VideoPlayer.ScaleType.CENTER_CROP -> {
pivotPointX = (viewWidth / 2).toInt()
pivotPointY = (viewHeight / 2).toInt()
}
else -> {
pivotPointX = (viewWidth / 2).toInt()
pivotPointY = (viewHeight / 2).toInt()
}
}

val matrix = Matrix()
matrix.setScale(scaleX, scaleY, pivotPointX.toFloat(), pivotPointY.toFloat())

setTransform(matrix)
}


 fun prepareVideo(t:SurfaceTexture) {

this.surface = Surface(t)
mp = MediaPlayer()
mp!!.setSurface(this.surface)

try
{
val afd = context.assets.openFd(url)
mp!!.setDataSource(afd.fileDescriptor, afd.startOffset, afd.length)
mp!!.prepareAsync()

mp!!.setOnPreparedListener(object:MediaPlayer.OnPreparedListener {
override fun onPrepared(mp:MediaPlayer) {
isMpPrepared = true
    mp.isLooping = true
mp.setVolume(0f, 0f)
mp.setOnVideoSizeChangedListener { mp, width, height ->
    mVideoWidth = width.toFloat()
    mVideoHeight = height.toFloat()
    updateTextureViewSize()
}
    //startPlay();
                    changePlayState()
 //iVideoPreparedListener.onVideoPrepared(video);
                    //adjustAspectRatio(VideoPlayer.this,mp.getVideoWidth(),mp.getVideoHeight());
                }


})
}
catch (e1:IllegalArgumentException) {
e1.printStackTrace()
}
catch (e1:SecurityException) {
e1.printStackTrace()
}
catch (e1:IllegalStateException) {
e1.printStackTrace()
}
catch (e1:IOException) {
e1.printStackTrace()
}

try
{

}
catch (e:IllegalArgumentException) {
e.printStackTrace()
}
catch (e:SecurityException) {
e.printStackTrace()
}
catch (e:IllegalStateException) {
e.printStackTrace()
}

try
{

}
catch (e:IllegalStateException) {
e.printStackTrace()
}

}

override fun onAttachedToWindow() {
super.onAttachedToWindow()
}


 fun startPlay():Boolean {
if (mp != null)
if (!mp!!.isPlaying)
{
mp!!.start()
return true
}

return false
}

 fun pausePlay() {
if (mp != null)
mp!!.pause()
}

 fun stopPlay() {
if (mp != null)
mp!!.stop()
}

 fun changePlayState() {
if (mp != null)
{
if (mp!!.isPlaying)
mp!!.pause()
else
mp!!.start()
}

}

 fun setOnVideoPreparedListener(iVideoPreparedListener:IVideoPreparedListener) {
this.iVideoPreparedListener = iVideoPreparedListener
}

private fun adjustAspectRatio(m_TextureView:TextureView, videoWidth:Int, videoHeight:Int) {
val viewWidth = m_TextureView.width
val viewHeight = m_TextureView.height
val aspectRatio = videoHeight.toDouble() / videoWidth

val newWidth:Int
val newHeight:Int
if (viewHeight > (viewWidth * aspectRatio).toInt())
{
 // limited by narrow width; restrict height
            newWidth = viewWidth
newHeight = (viewWidth * aspectRatio).toInt()
}
else
{
 // limited by short height; restrict width
            newWidth = (viewHeight / aspectRatio).toInt()
newHeight = viewHeight
}
val xoff = (viewWidth - newWidth) / 2
val yoff = (viewHeight - newHeight) / 2
Log.v(TAG, ("video=" + videoWidth + "x" + videoHeight +
" view=" + viewWidth + "x" + viewHeight +
" newView=" + newWidth + "x" + newHeight +
" off=" + xoff + "," + yoff))

val txform = Matrix()
m_TextureView.getTransform(txform)
txform.setScale(newWidth.toFloat() / viewWidth, newHeight.toFloat() / viewHeight)
 //txform.postRotate(10);          // just for fun
        txform.postTranslate(xoff.toFloat(), yoff.toFloat())
m_TextureView.setTransform(txform)
}

 fun seekTo(pos:Int) {
if (mp != null)
{
mp!!.seekTo(pos)
}
}

companion object {

private val TAG = "VideoPlayer"
}

}

