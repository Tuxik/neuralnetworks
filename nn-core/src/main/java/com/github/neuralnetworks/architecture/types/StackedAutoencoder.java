package com.github.neuralnetworks.architecture.types;

import com.github.neuralnetworks.architecture.Layer;

/**
 * Stacked autoencoder
 */
public class StackedAutoencoder extends DNN {

    public StackedAutoencoder(Layer input) {
	super();
	addLayer(input);
    }

    public void addLevel(Layer hidden, Layer output, boolean addBias) {
	Layer input = getOutputLayer();

	if (input == null) {
	    throw new IllegalArgumentException("At least one layer must be added before adding levels");
	}

	if (input.getNeuronCount() != output.getNeuronCount()) {
	    output.setNeuronCount(input.getNeuronCount());
	}

	addNeuralNetwork(new Autoencoder(input, hidden, output, addBias));
    }
}
