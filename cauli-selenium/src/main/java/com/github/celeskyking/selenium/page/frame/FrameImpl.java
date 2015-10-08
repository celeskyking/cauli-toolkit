package com.github.celeskyking.selenium.page.frame;

import com.github.celeskyking.selenium.location.ElementLocation;
import com.github.celeskyking.selenium.page.IPage;
import com.github.celeskyking.selenium.page.PageImpl;

/**
 * Created by sky on 15/9/30
 */
public abstract class FrameImpl extends PageImpl {

    private IPage parent;

    public FrameImpl(IPage page) {
        super(page.browser());
        this.parent = page;
    }


    @Override
    public IPage parent() {
        return parent;
    }

    private static class IndexFrameImpl extends FrameImpl{

        public int index;

        public IndexFrameImpl(IPage page,int index) {
            super(page);
            this.index = index;
        }

        @Override
        public void locate() {
            parent().frame(index);
        }
    }

    private static class LocationFrameImpl extends FrameImpl{

        public ElementLocation location;

        public LocationFrameImpl(IPage page,ElementLocation location) {
            super(page);
            this.location = location;
        }

        @Override
        public void locate() {
            parent().frame(location);
        }
    }
}
