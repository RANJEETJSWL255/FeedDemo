package sample.com.feeddemo

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class FeedActivityTest  {
    @get: Rule
    val activityRule = ActivityTestRule(FeedActivity::class.java)

    lateinit var launchedActivity: FeedActivity

    @Before
    fun setUp(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        launchedActivity = activityRule.launchActivity(intent)
    }

    @Test
    fun testLaunch(){
        val view: View? = launchedActivity.findViewById(R.id.recyclerViewFeed)
        assert(view != null)
    }

    @Test
    fun testWithData(){
        val view: RecyclerView? = launchedActivity.findViewById(R.id.recyclerViewFeed)
        if(view != null){
            var itemCount: Int? = view.adapter?.itemCount
            assert(itemCount != null && itemCount >0)
        }else{
            assert(false)
        }
    }

    @Test
    fun testWithError(){
        val view: RecyclerView? = launchedActivity.findViewById(R.id.recyclerViewFeed)
        if(view != null){
            var itemCount: Int? = view.adapter?.itemCount
            assert(itemCount == null || itemCount  == 0)
        }else{
            assert(false)
        }
    }
}