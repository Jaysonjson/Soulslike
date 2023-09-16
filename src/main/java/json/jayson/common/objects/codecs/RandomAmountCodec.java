package json.jayson.common.objects.codecs;

public class RandomAmountCodec {

    public final int min;
    public final int max;

    public RandomAmountCodec(int min, int max) {
        this.max = max;
        this.min = min;
    }

}
