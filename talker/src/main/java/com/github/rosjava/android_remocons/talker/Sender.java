package com.github.rosjava.android_remocons.talker;

import org.ros.node.ConnectedNode;
import org.ros.node.NodeMain;
import org.ros.node.topic.Publisher;
import org.ros.namespace.GraphName;
import org.ros.concurrent.CancellableLoop;


public  class Sender implements NodeMain{

    public Publisher publisherSDMa;
    public Publisher publisherWGT;
    public Publisher publisherHRM;

    public NodeMain node;

    public Sender() {
//     TODO Auto-generated constructor stub

    }


    @Override
    public void onStart(ConnectedNode node) {
    //public void onStart(Node node) {

        publisherSDMa = node.newPublisher("ANT", std_msgs.String._TYPE);

        final CancellableLoop aLoop = new CancellableLoop() {
            @Override protected void loop() throws InterruptedException {
                Thread.sleep(1000);
            }
        };

        // This CancellableLoop will be cancelled automatically when the Node
        // shuts down.
        node.executeCancellableLoop(aLoop);

    }

    public void publicar(String data)
    {

        std_msgs.String msg = (std_msgs.String)publisherSDMa.newMessage();
        msg.setData(data);
        publisherSDMa.publish(msg);

    }

    @Override
    public void onShutdown(org.ros.node.Node noder) {

        noder = null;
    }

    @Override
    public void onShutdownComplete(org.ros.node.Node node) {

    }

    @Override
    public void onError(org.ros.node.Node node, Throwable throwable) {

    }


    @Override
    public GraphName getDefaultNodeName() {
        return GraphName.of("Sender");
    }
}


