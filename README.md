# Quick Data Structure

## Project Requirements

* The QuickPopDataStructure and QuickPushDataStructure classes should be thread-safe, meaning they can be safely used in a multithreaded application. 
Multiple threads should be able to concurrently add or remove objects from the data structure without causing any race conditions or unexpected behavior.

* The data structure should be generic, meaning it can accept any item of any type.

* The pop method should retrieve the maximum element.


# Quick Pop Data Structure

The QuickPopDataStructure is a Java class that implements a custom data structure for efficiently retrieving the maximum value from a collection of objects. 
This class provides a push method for adding objects to the collection in O(n) time and a pop method for retrieving the maximum value in O(1) time.


# Quick Push Data Structure

The QuickPushDataStructure is a Java class that implements a custom data structure for efficiently adding objects to a collection. 
This class provides a push method for adding objects to the collection in O(1) time, and a pop method for retrieving the maximum value in O(n) time.



# Usage
* To use the ```QuickPushDataStructure``` or ```QuickPopDataStructure``` , simply create a new instance of the class: 
``` 
QuickPushDataStructure<T> dataStructure = new QuickPushDataStructure<>(); 
```
Note that T can be replaced by any type.

* You can then add objects to the collection using the push method:
``` 
dataStructure.push(5);
dataStructure.push(7);

```
* To retrieve the maximum value from the collection, use the pop method:
```
Integer maxValue = dataStructure.pop(); // returns 7
```

