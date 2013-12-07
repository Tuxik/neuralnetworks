package com.github.neuralnetworks.architecture.types;

import com.github.neuralnetworks.architecture.Conv2DConnection;
import com.github.neuralnetworks.architecture.ConvGridLayer;
import com.github.neuralnetworks.architecture.Matrix;
import com.github.neuralnetworks.architecture.Pooling2DConnection;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiAveragePooling2D;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiConv2DSigmoid;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiConv2DSoftReLU;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiMaxPooling2D;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiStochasticPooling2D;
import com.github.neuralnetworks.calculation.neuronfunctions.AparapiTanh;

/**
 * Factory for connections
 */
public class ConnectionFactory {

    public static Conv2DConnection convConnection(ConvGridLayer inputLayer, int featureMapRows, int featureMapColumns, int featureMaps) {
	Conv2DConnection result = new Conv2DConnection(inputLayer);

	for (int i = 0; i < featureMaps; i++) {
	    result.addFilter(new Matrix(featureMapRows, featureMapColumns));
	}

	return result;
    }

    public static Conv2DConnection convSigmoidConnection(ConvGridLayer inputLayer, int featureMapRows, int featureMapColumns, int featureMaps) {
	Conv2DConnection result = convConnection(inputLayer, featureMapRows, featureMapColumns, featureMaps);
	result.getOutputLayer().setConnectionCalculator(new AparapiConv2DSigmoid());
	return result;
    }

    public static Conv2DConnection convSoftReLUConnection(ConvGridLayer inputLayer, int featureMapRows, int featureMapColumns, int featureMaps) {
	Conv2DConnection result = convConnection(inputLayer, featureMapRows, featureMapColumns, featureMaps);
	result.getOutputLayer().setConnectionCalculator(new AparapiConv2DSoftReLU());
	return result;
    }

    public static Conv2DConnection convTanhConnection(ConvGridLayer inputLayer, int featureMapRows, int featureMapColumns, int featureMaps) {
	Conv2DConnection result = convConnection(inputLayer, featureMapRows, featureMapColumns, featureMaps);
	result.getOutputLayer().setConnectionCalculator(new AparapiTanh());
	return result;
    }
    
    public static Pooling2DConnection maxPoolingConnection(ConvGridLayer inputLayer, int regionRows, int regionCols) {
	return new Pooling2DConnection(inputLayer, regionRows, regionCols, new AparapiMaxPooling2D());
    }
    
    public static Pooling2DConnection averagePoolingConnection(ConvGridLayer inputLayer, int regionRows, int regionCols) {
	return new Pooling2DConnection(inputLayer, regionRows, regionCols, new AparapiAveragePooling2D());
    }
    
    public static Pooling2DConnection stochasticPoolingConnection(ConvGridLayer inputLayer, int regionRows, int regionCols) {
	return new Pooling2DConnection(inputLayer, regionRows, regionCols, new AparapiStochasticPooling2D());
    }
}
