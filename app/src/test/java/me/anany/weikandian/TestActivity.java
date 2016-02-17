package me.anany.weikandian;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import me.anany.weikandian.ui.activity.MainActivity;

/**
 * Created by anany on 16/2/17.
 * <p>
 * Email:zhujun2730@gmail.com
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class TestActivity {

    @Test
    public void testActivity() {
        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        Assert.assertNotNull(mainActivity);

        Assert.assertEquals(mainActivity.getTitle(), "WeiKanDian");
    }
}
