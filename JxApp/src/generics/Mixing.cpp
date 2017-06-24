package generics;

// Примеси в C++. Элегантный подход к реализации примесей основан 
// на использование параметризованных типов, где примесь представляется классом,
// производным от параметра-типа!

#include "stdafx.h"


#include <string>
#include <ctime>
#include <iostream>

template<class T> class TimeStamped : public T {
    long timeStamp;
  public:
    TimeStamped() { timeStamp = time(0); }
    long getStamp() { return timeStamp; }
};

template<class T> class SerialNumbered : public T {
    long serialNumber;
    static long counter;
  public:
    SerialNumbered() { serialNumber = counter++; }
    long getSerialNumber() { return serialNumber; }
};

template<class T> long SerialNumbered<T>::counter = 1;

class Basic {
    std::string value;
  public:
    void set(const std::string& val) { value = val; }
    std::string get() { return value; }
};


int _tmain(int argc, _TCHAR* argv[]) {
  TimeStamped<SerialNumbered<Basic>> mixin1, mixin2;
  mixin1.set("test string 1");
  mixin2.set("test string 2");
  std::cout << mixin1.get() << " " << mixin1.getStamp() << " " << mixin1.getSerialNumber();
  std::cout << mixin2.get() << " " << mixin2.getStamp() << " " << mixin2.getSerialNumber();
  return 0;
}
