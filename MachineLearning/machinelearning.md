# Supervised Learning

## Regression(回归)

The output of the target function ƒ is "scalar"
函数的输出是一个标量(一个值)

### Basic Procedure

<img src="Source/image-20220108182802020.png" alt="image-20220108182802020" style="zoom:50%;" />

### Function Set

<img src="Source/image-20220108183031204.png" alt="image-20220108183031204" style="zoom:50%;" />

### Loss Function

以下为最常用的loss function定义，可自定义

<img src="Source/image-20220108183130629.png" alt="image-20220108183130629" style="zoom:50%;" />

产出Estimation error

### Gradient Descent

#### Loss formula

<img src="Source/image-20220109211401259.png" alt="image-20220109211401259" style="zoom:67%;" />

#### Update parameter

![image-20220109212317711](Source/image-20220109212317711.png)

## What to do with errors

### Bias(underfitting)

Model cannot even fit training examples, means that this model has large bias.

For this situation, redesign your model:

1. Add more features as input
2. A more complex model(take higher order variables into consideration, for example x<sup>2</sup>)

### Large variance(overfitting)

Model can fit the training data, but large error on testing data, means that this model provavly has large variance.

For this situation:

1. Collecting more data
2. Regularization

## Normalization

### Min-Max

使各影响因素产生的影响归一，通常映射到\[0,1], [-1,1]

![[公式]](Source/frac{x-x_{min}}{x_{max}-x_{min}}.svg+xml)

### Z-Score

将原有的数值分布转化为均值为0，方差为1的分布

![[公式]](Source/sigma+}.svg+xml)

## Classification

1) Binary Classification

2) Multi-class Classification

### Linear Model

#### Deep Learning

#### SVM, decision tree, K-NN

### Structured Learning --Beyond Classification

# Semi-supervised Learning

# Transfer Learning

# Unsupervised Learning

# Reinforcement Learning