import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from sklearn.impute import SimpleImputer        # For missing data
from sklearn.compose import ColumnTransformer   # Creating additional columns
from sklearn.preprocessing import OneHotEncoder
from sklearn.preprocessing import LabelEncoder   # To encode dependent variable

# imports of 'Splitting Dataset into the Training Set and Test Set'
from sklearn.model_selection import train_test_split

# imports of 'Feature Scaling'
from sklearn.preprocessing import StandardScaler

# importing 'single linear regression model'
from sklearn.linear_model import LinearRegression



# BEGINNING OF THE DATA PREPROCESSING
#---------------------------------------------------------------------------
"""
### DATA PREPROCESSING
- Importing Dataset
- Encoding Non-numerical Data
- Splitting Dataset into the Training Set and Test Set
- Feature Scaling
"""



"""
# csv converison
df = pd.read_excel('data.xlsx')
df.to_csv('data.csv', index=False)          # Save as CSV
print("CSV file has been created successfully.")
"""



# importing dataset
dataset = pd.read_csv('data.csv')
X = dataset.iloc[:, :-1].values    # iloc stands for locate indexes
""" [:, :-1] first : takes all the rows, second : takes all the columns except the last one"""
y = dataset.iloc[:, -1].values

## Splitting Dataset into the Training Set and Test Set
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.5)     # remove random_state in real life applications



## Feature Scaling
"""
FEATURE SCALING
Feature scaling is a preprocessing step commonly applied in machine learning to standardize or normalize the
range of independent variables or features of your dataset. The goal is to ensure
that all features contribute equally to the computation of distances, weights, and other mathematical calculations
performed by machine learning algorithms.

Here's why you typically apply feature scaling after splitting your data into training and test sets:

1. Preventing Data Leakage:
- If you scale your features before splitting the data, you might use information from the entire
dataset to compute the scaling parameters. This could lead to information
leakage from the test set into the training set, compromising the independence of the two sets.
- Scaling after splitting ensures that each set is treated independently. The scaling parameters (mean, standard deviation, etc.)
are computed only from the data within each respective set.

2. Reflecting Real-world Scenarios:
- In real-world scenarios, when you deploy your machine learning model, you'll encounter new,
unseen data that wasn't part of your training set. Therefore, it's important to simulate this scenario in
your evaluation by treating your test set as a representation of future, unseen data.
- By scaling the features separately for training and test sets, you better simulate how your model would perform on new, unseen data.
"""

"""
Techniques of Feature Scaling
1. Standardization: x(stand) = ( x- mean(x) ) / ( std(x) )
2. Normalization: x(norm) = ( x - min(x) ) / ( max(x) - min(x) )

* Use standardization (Z-score normalization) when your features have different units or when the algorithm you're using is
sensitive to the scale of the features.

* Use normalization (Min-Max scaling) when you want to scale the features to a specific range,
especially when working with algorithms that perform better when input features are within a similar numerical range.

* Standardization shouldnt be applied to the dummy variables sucah as altered variables with string names etc.
"""



sc = StandardScaler()
X_train  = sc.fit_transform(X_train)        # First columns are dummy variables of string country names, this is the reason they are omitted
X_test = sc.transform(X_test)


# END OF DATA PREPROCESSING
#---------------------------------------------------------------------------






### BEGINNING OF LINEAR REGRESSION
#---------------------------------------------------------------------------
"""
Assumptions of Linear Regression:
1) Linearity
2) Independence of Residuals
3) Homoscedasticity
4) Normality of Residuals
5) No Perfect Multicollinearity
6) No Autocorrelation of Residuals
7) Correct Specification ( exclusion of the unnecessary variables )
#In the context of linear regression, residuals are the differences between the observed (actual) values of the
dependent variable and the predicted values produced by the regression model.
e(i) = y(i) - y_(i)
"""


## Training
regressor = LinearRegression()
regressor.fit(X_train, y_train)


## Predicting the Test Set results
y_pred = regressor.predict(X_test)


## Visualizing the Training Set results
plt.scatter(X_train, y_train, color='red')
plt.plot(X_train, regressor.predict(X_train), color='blue')
plt.title('Batch Size vs Time')
plt.xlabel('Batch Size')
plt.ylabel('Time')
plt.show()


## Visualizing the Test Set results
plt.scatter(X_test, y_test, color='red')
plt.plot(X_train, regressor.predict(X_train), color='blue')     # Keep the regression as it is
plt.title('Batch Size vs Time (Test)')
plt.xlabel('Batch Size')
plt.ylabel('Time')
plt.show()




## Regression Model ( ax+b )
print(regressor.coef_ , "*X " , regressor.intercept_)



## Easier way to predict
print(regressor.predict([[12]]))
print(12*regressor.coef_ + regressor.intercept_)        # long version

# END OF LINEAR REGRESSION
#---------------------------------------------------------------------------
