package sfBugsNew;

import com.google.common.base.Preconditions;

import edu.umd.cs.findbugs.annotations.ExpectWarning;
import edu.umd.cs.findbugs.annotations.NoWarning;

public class Feature326 {
    @ExpectWarning("UC_USELESS_CONDITION")
    public int deadCode(int x) {
        if(x > 10) {
            return 1;
        }
        if(x <= 10) {
            return -1;
        }
        return 0;
    }
    
    @NoWarning("UC_USELESS_CONDITION")
    public int fp(int x) {
        if( x >= -1 )
        {
            int result = x == -1 ? 1 : 2;
            return result;
        }
        return 0;
    }
    
    @ExpectWarning("UC_USELESS_CONDITION")
    public int condition(int x) {
        if(x > 10 && x > 5) {
            return 2; 
        }
        return 1;
    }
    
    @NoWarning("UC_USELESS_CONDITION")
    public void testFinally(boolean c) {
        try {
            if(c) {
                System.out.println("1");
            }
        }
        finally {
            if(c)
                System.out.println("2");
            else
                System.out.println("3");
        }
    }

    @NoWarning("UC_USELESS_CONDITION")
    public void testTwoSlotParameters(long l1, int m2, long l3) {
        if(m2 == 199 || m2 == 200) {
            System.out.println("test");
        }
        if(l3 == 199) {
            System.out.println("test");
        }
        if(l1 == 199) {
            System.out.println("test");
        }
        System.out.println("best");
    }

    @NoWarning("UC_USELESS_CONDITION")
    public void testInverseCondition(int p) throws Exception {
        if(4<p) {
            throw new Exception();
        } else if(4>p) {
            System.out.println("!!!");
        }
        System.out.println("???");
    }

    @ExpectWarning("UC_USELESS_CONDITION")
    public void testInverseCondition2(int p) throws Exception {
        if(4<p) {
            throw new Exception();
        } else if(4>=p) {
            System.out.println("!!!");
        }
        System.out.println("???");
    }

    @ExpectWarning("UC_USELESS_CONDITION")
    public int localVarTest() throws Exception {
        int var = condition(1);
        if(var < 1) {
            return 0;
        }
        if(var > 0) {
            System.out.println("1");
        }
        if(var > 0) {
            System.out.println("1");
        }
        return 2;
    }

    @NoWarning("UC_USELESS_CONDITION")
    public void testAssert(int x) {
        if(x < 0) {
            return;
        }
        Preconditions.checkArgument(x >= 0);
        System.out.println(x);
    }

    boolean c1, d1;
    
    @NoWarning("UC_USELESS_CONDITION")
    public int testFinally2(boolean c) {
        try {
            if(c) {
                return 1;
            }
            if(c1) {
                return 2;
            }
        }
        catch(Throwable t) {
            d1 = true;
            try {
                if(d1) {
                    return 1;
                }
            }
            finally {
                d1 = false;
            }
        }
        finally {
            if(c)
                c1 = true;
            else
                c1 = false;
        }
        return 0;
    }

    @ExpectWarning("UC_USELESS_CONDITION")
    public int testFinallyUseless(boolean c, boolean d) {
        if(!c) {
            try {
                System.out.println("before");
                return 1;
            }
            catch(Throwable e) {
                // ignore
            }
            finally {
                try {
                    if (c)
                        c1 = true;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }

    @ExpectWarning("UC_USELESS_CONDITION")
    public int testUselessInCatch(boolean c, String d) {
        try {
            if(c) {
                Integer.parseInt(d);
            }
        }
        catch(NumberFormatException ex) {
            if(!c) {
                System.out.println("useless");
            }
        }
        return 0;
    }

    @NoWarning("UC_USELESS_CONDITION")
    public int testFinallyOk(boolean c) {
        try {
            System.out.println("before");
            if (!c) {
                return 1;
            }
            System.out.println("after");
        } catch (Throwable e) {
            // ignore
        } finally {
            try {
                if (c)
                    c1 = true;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    @ExpectWarning("UC_USELESS_CONDITION")
    public int testLong(long l) {
        if(l < 0) {
            return 1; 
        }
        if(l >= 0) {
            return 2;
        }
        return 3;
    }

    @ExpectWarning("UC_USELESS_CONDITION")
    public int testLongInverse(long l) {
        if(10 < l) {
            return 1; 
        }
        if(10 >= l) {
            return 2;
        }
        return 3;
    }

    @NoWarning("UC_USELESS_CONDITION")
    public int testLongInverseOk(long l) {
        if(-10 < l) {
            return 1; 
        }
        if(-10 > l) {
            return 2;
        }
        return 3;
    }

    @ExpectWarning("UC_USELESS_CONDITION_TYPE")
    public int testLongMax(long l) {
        if(l > 0 && l <= Long.MAX_VALUE) {
            return 1; 
        }
        return 0;
    }
    
    @ExpectWarning("UC_USELESS_CONDITION_TYPE")
    public int testLongMin(long l) {
        if(l < 0 && l >= Long.MIN_VALUE) {
            return 1; 
        }
        return 0;
    }
    
    @ExpectWarning("UC_USELESS_CONDITION_TYPE")
    public int testCharMax(char c) {
        if(c < 0x10FFFF) {
            return 1; 
        }
        return 0;
    }
    
    @NoWarning("UC_USELESS_CONDITION_TYPE")
    @ExpectWarning("INT_VACUOUS_COMPARISON")
    public int testIntMax(int i) {
        if(i > 0 && i <= Integer.MAX_VALUE) {
            return 1;
        }
        return 0;
    }
}
