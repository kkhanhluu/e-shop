/**
 * Creating a sidebar enables you to:
 - create an ordered group of docs
 - render a sidebar for each doc of that group
 - provide next/previous navigation

 The sidebars can be generated from the filesystem, or explicitly defined here.

 Create as many sidebars as you want.
 */

// @ts-check

/** @type {import('@docusaurus/plugin-content-docs').SidebarsConfig} */
const sidebars = {
  tutorialSidebar: [
    'index',
    'getting-started',
    {
      type: 'category',
      label: 'Explore',
      collapsible: true,
      collapsed: false,
      items: [
        {
          type: 'category',
          label: 'Architecture',
          collapsible: true,
          collapsed: false,
          items: [
            {
              type: 'doc',
              id: 'explore/architecture/overview',
              label: 'Overview',
            },
            {
              type: 'doc',
              id: 'explore/architecture/code-flow',
              label: 'Code Flow',
            },
          ],
        },

        {
          type: 'category',
          label: 'Code',
          collapsible: true,
          collapsed: false,
          items: [
            {
              type: 'doc',
              id: 'explore/code/monorepo',
              label: 'Monorepo',
            },
            {
              type: 'doc',
              id: 'explore/code/simplified-cqrs',
              label: 'Simplified CQRS',
            },
            {
              type: 'doc',
              id: 'explore/code/event-sourcing',
              label: 'Event Sourcing',
            },
            {
              type: 'doc',
              id: 'explore/code/transactional-outbox-pattern',
              label: 'Transactional outbox pattern',
            },
            {
              type: 'doc',
              id: 'explore/code/saga-pattern',
              label: 'Saga pattern',
            },
            {
              type: 'doc',
              id: 'explore/code/grpc',
              label: 'gRPC',
            },
          ],
        },
      ],
    },
  ],
};

module.exports = sidebars;
