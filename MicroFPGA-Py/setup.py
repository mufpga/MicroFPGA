from setuptools import setup

setup(name='microfpga',
      version='1.0',
      description='Example module to control MicroFPGA',
      url='http://github.com/MicroFPGA',
      author='Joran Deschamps',
      author_email='joran.deschamp@embl.de',
      license='LGPL',
      packages=['microfpga'],
      install_requires=[
          'pyserial',
      ],
      zip_safe=False)