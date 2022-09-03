package com.example.learnuikotlin.VideoMedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.learnuikotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: VideoAdapter

    private val videos = ArrayList<Video>()
    private val exoPlayerItem = ArrayList<ExoPlayerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        videos.add(
            Video(
                "Big Buck Bunny",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            )
        )

        videos.add(
            Video(
                "Elephant Dream",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
            )
        )

        videos.add(
            Video(
                "For Bigger Blazes",
                "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4"
            )
        )

        adapter = VideoAdapter(this, videos, object : VideoAdapter.OnVideoPreparedListeners {
            override fun OnVideoPrepared(item: ExoPlayerItem) {
                exoPlayerItem.add(item)
            }
        })

        binding.viewPager2.adapter = adapter

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val previousIndex = exoPlayerItem.indexOfFirst { it.exoPlayer.isPlaying }
                if(previousIndex != -1) {
                    val player = exoPlayerItem[previousIndex].exoPlayer
                    player.pause()
                    player.playWhenReady = false
                }
                val newIndex = exoPlayerItem.indexOfFirst { it.position == position }
                if(newIndex != -1) {
                    val player = exoPlayerItem[newIndex].exoPlayer
                    player.playWhenReady = true
                    player.play()
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()

        val index = exoPlayerItem.indexOfFirst { it.position == binding.viewPager2.currentItem }
        if(index != -1) {
            val player = exoPlayerItem[index].exoPlayer
            player.pause()
            player.playWhenReady = false
        }
    }

    override fun onResume() {
        super.onResume()

        val index = exoPlayerItem.indexOfFirst { it.position == binding.viewPager2.currentItem }
        if(index != -1) {
            val player = exoPlayerItem[index].exoPlayer
            player.playWhenReady = true
            player.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(exoPlayerItem.isNotEmpty()) {
            for(item in exoPlayerItem) {
                val player = item.exoPlayer
                player.stop()
                player.clearMediaItems()
            }
        }
    }
}