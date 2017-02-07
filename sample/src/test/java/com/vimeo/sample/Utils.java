package com.vimeo.sample;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.vimeo.sample.stag.generated.Stag;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Utils class for testing type adapter generation.
 * <p>
 * Created by restainoa on 1/30/17.
 */
public final class Utils {

    private Utils() {
    }

    @Nullable
    private static <T> TypeAdapter<T> getTypeAdapter(@NonNull Class<T> clazz) {
        Gson gson = new Gson();
        Stag.Factory factory = new Stag.Factory();
        TypeToken<T> innerModelType = TypeToken.get(clazz);
        return factory.create(gson, innerModelType);
    }

    /**
     * Verifies that a TypeAdapter was generated for the specified class.
     *
     * @param clazz the class to check.
     * @param <T>   the type of the class, used internally.
     * @throws Exception throws an exception if an adapter was not generated.
     */
    public static <T> void verifyTypeAdapterGeneration(@NonNull Class<T> clazz) throws Exception {
        TypeAdapter<T> typeAdapter = getTypeAdapter(clazz);
        assertNotNull("Type adapter shouldn't have been generated by Stag", typeAdapter);
    }

    /**
     * Verifies that a TypeAdapter was NOT generated for the specified class.
     *
     * @param clazz the class to check.
     * @param <T>   the type of the class, used internally.
     * @throws Exception throws an exception if an adapter was generated.
     */
    public static <T> void verifyNoTypeAdapterGeneration(@NonNull Class<T> clazz) throws Exception {
        TypeAdapter<T> typeAdapter = getTypeAdapter(clazz);
        assertNull("Type adapter should have been generated by Stag", typeAdapter);
    }

}
