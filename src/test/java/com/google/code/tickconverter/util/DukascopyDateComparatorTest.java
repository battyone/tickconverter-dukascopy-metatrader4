/**
 * Copyright (C) 2012 Karsten Schulz <lennylinux.ks@googlemail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT
 * OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.google.code.tickconverter.util;

import java.util.ArrayList;
import java.util.Collections;

import junit.framework.Assert;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.code.tickconverter.bean.DukascopyBean;

public class DukascopyDateComparatorTest
{

    private static DukascopyDateComparator comperator;

    private DukascopyBean first;

    private DukascopyBean second;

    @BeforeClass
    public static void setUpBefore()
        throws Exception
    {
        comperator = new DukascopyDateComparator();
    }

    @Before
    public void setUp()
        throws Exception
    {
        first = new DukascopyBean();
        second = new DukascopyBean();
    }

    @Test
    public void testCompareLess()
    {
        first.setTimeStamp( new DateTime( 2012, 8, 22, 0, 0, 0 ) );
        second.setTimeStamp( new DateTime( 2012, 8, 22, 0, 0, 1 ) );

        Assert.assertEquals( -1, comperator.compare( first, second ) );
    }

    @Test
    public void testCompareGreater()
    {
        first.setTimeStamp( new DateTime( 2012, 8, 22, 0, 0, 0 ) );
        second.setTimeStamp( new DateTime( 2012, 8, 22, 0, 0, 1 ) );

        Assert.assertEquals( 1, comperator.compare( second, first ) );
    }

    @Test
    public void testCompareEquals()
    {
        first.setTimeStamp( new DateTime( 2012, 8, 22, 0, 0, 0 ) );
        second.setTimeStamp( first.getTimeStamp() );
        Assert.assertEquals( 0, comperator.compare( second, first ) );
    }

    @Test
    public void testCompareList()
    {
        ArrayList<DukascopyBean> list = new ArrayList<>();
        first.setTimeStamp( new DateTime( 2012, 8, 22, 0, 0, 0 ) );
        second.setTimeStamp( new DateTime( 2012, 8, 22, 0, 0, 1 ) );

        list.add( second );
        list.add( first );
        Collections.sort( list, comperator );

        Assert.assertEquals( first, list.get( 0 ) );
    }
}
