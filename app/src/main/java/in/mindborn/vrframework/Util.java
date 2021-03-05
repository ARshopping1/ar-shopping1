package in.mindborn.vrframework;

import android.net.Uri;
import android.util.Log;

import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.Material;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ShapeFactory;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.Objects;
import java.util.function.Consumer;

public class Util
{

    public static float scale = 600.0f;

    public static TransformableNode createSphere(ArFragment arFragment, float x, float y, float z, float radius, Node parent, Material material)
    {
        Log.d("test1", "createSphere: " + x + ", " + y + ", " + z + ", " + radius + ", " + parent);

        ModelRenderable sphere = ShapeFactory.makeSphere(radius, new Vector3(0, 0, 0), material);
        Log.d("test1", "aftercreateSphere: " + x + ", " + y + ", " + z + ", " + radius + ", " + parent);
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());

        transformableNode.setRenderable(sphere);
        parent.addChild(transformableNode);
        transformableNode.setLocalPosition(new Vector3(x, y, z));
        transformableNode.setLocalScale(new Vector3(1f, 1f, 1f));

        Log.d("test1", "sphere has created");
        return transformableNode;
    }

    public static void createRing(String str, ArFragment arFragment, float x, float y, float z, float radius, final Node parent, Material material)
    {


        ModelRenderable.builder().setSource(Objects.requireNonNull(arFragment.getContext()), Uri.parse(str)).build().thenAccept((new Consumer()
        {
            public void accept(Object var1)
            {
                this.accept((ModelRenderable) var1);
            }

            public final void accept(ModelRenderable it)
            {
                if (it != null)
                {
                    TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());
                    transformableNode.setRenderable(it);
//        transformableNode.setParent(parent);
                    parent.addChild(transformableNode);
                    transformableNode.setLocalPosition(new Vector3(x, y, z));
                    Vector3 scalar = new Vector3(1f, 1f, 1f).scaled(radius);
                    System.out.println("scalar = " + scalar);
                    transformableNode.setLocalScale(scalar);

//        arFragment.getArSceneView().getScene().addChild(transformableNode);
//        transformableNode.select();
                    Log.d("test1", "ring has created");

                } else
                {
                    Log.d("test", "it==null");
                }
            }
        }));
    }


    public static Vector3 getWorldLocation(Node n)
    {
        if (n instanceof AnchorNode)
            return Vector3.zero();

        Vector3 cp = n.getLocalPosition();
        Vector3 pp = getWorldLocation(n.getParent());
        // Vector3 result = Vector3.add(cp,pp);
        //  Log.d("test3","getWorldLocation "+ result);
        return Vector3.add(cp, pp);

    }

}
