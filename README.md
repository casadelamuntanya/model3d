# model3d

Casa de la Muntanya's model3d is a blank canvas to project digital data layers and custom simulations over a physical 3D representation of the Andorran orography, using the [Processing](https://processing.org) environment.

This library provides the base interfaces and classes to develop visualizations.

## Getting started
Clone this repo into the Processing libraries directory.

[re]start Processing and include desired packages.

```java
import ad.casadelamuntanya.model3d.*;
import ad.casadelamuntanua.model3d.feature.*;
import ad.casadelamuntanua.model3d.surface.*;

import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
```

There is a [known issue](https://github.com/casadelamuntanya/model3d/issues/4) with the sketch requiring to import CoordinateSequenceFilter from jts.

## Development
This code is intended to keep as much generic as possible and not all functionalities developed in a custom sketch must be included. Cool stuff may be, though.

To include new features into the library, add/edit files in the **src** directory and un the provided bash build script the compile it.

```bash
./build
```

**Note that `src/code` must contain jts.jar and warp.jar files**

New .jar file will be generated and moved to the appropriate directory. Commit changes and open a Pull Request.
